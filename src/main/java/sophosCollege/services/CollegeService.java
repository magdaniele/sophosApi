package sophosCollege.services;
import java.sql.*;
import java.util.ArrayList;

import connection.connection;
import sophosCollege.classes.College;
import sophosCollege.classes.Course;
import sophosCollege.classes.Student;


public class collegeService {

	public static String addNewCollege(College college) {
		
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `college` (`name`,`nit`,`city`,`country`) VALUES (?,?,?,?)";
			
			statement = conn.prepareStatement(query);
			statement.setString(1, college.getName());
			statement.setLong(2, college.getNit());
			statement.setString(3, college.getCity());
			statement.setString(4, college.getCountry());
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to create collage: "+e.getMessage());
		}
		
		return result;
		
	}
	
	public static String enrollStudent(long nit, long ccStudent, int idCourse) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		Course course = courseService.findCourse(nit, idCourse);
		Boolean CourseIsAvailable = courseHasStatus(nit, ccStudent, idCourse, "ENRROLLED");
		if (CourseIsAvailable) {
			return "Error enrolling student because course has been registered before";
		}
		CourseIsAvailable = courseHasStatus(nit, ccStudent, idCourse, "APPROVED");
		if (CourseIsAvailable) {
			return "Error enrolling student because course has been given before";
		}
		CourseIsAvailable = courseHasStatus(nit, ccStudent, course.getPrerequisite(), "APPROVED");
		if (!CourseIsAvailable&&course.getPrerequisite()!=0) {
			return "Error enrolling student because he need to pass one course before";
		}
		
		try {
		
			String query = "INSERT INTO studentcourses (idCourse, idStudent, Status, semester, collegeId)"
					+ "WITH COURSE AS ("
					+ "        SELECT *"
					+ "        FROM course"
					+ "        WHERE id=? AND collegeId=? AND active=TRUE AND availableSpaces>0),"
					+ "    STUDENT AS ("
					+ "        SELECT *"
					+ "        FROM student"
					+ "        WHERE cc=? AND collegeId=? AND active=TRUE)"
					+ "SELECT COURSE.id,STUDENT.cc, ?,STUDENT.semester, ?"
					+ " FROM COURSE JOIN STUDENT ON COURSE.collegeId= STUDENT.collegeId";
			
			statement = conn.prepareStatement(query);
			statement.setInt(1, idCourse);
			statement.setLong(2, nit);
			statement.setLong(3, ccStudent);
			statement.setLong(4, nit);
			statement.setString(5,"ENRROLLED");
			statement.setLong(6, nit);
			statement.execute();
			course.setAvailableSpaces(course.getAvailableSpaces()-1);
			courseService.handleCourse(course, nit);
			Student student = studentService.findStudent(nit,ccStudent);
			student.setCreditsNumber(student.getCreditsNumber()-course.getnCredits());
			studentService.handleStudent(student, nit);
			result = "Registro Exitoso!!!";
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to enroll student: "+e.getMessage());
		}
		return result;
	}

	private static Boolean courseHasStatus(long nit, long ccStudent, int prerequisite, String status) {
		Boolean result = false;
		Course course = courseService.findEnrrolledCourse(nit, ccStudent, prerequisite, status);
		if(course!=null) {
			return true;
		}
		return result;
	}

	public static ArrayList<Student> findStudentsByCourse(Long nit, int idCourse) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ArrayList<Student> students = new ArrayList<Student>();
		
		try {

			String query = "SELECT * "
					+ "FROM `student` "
					+ "WHERE collegeId = ? "
					+ "  AND active = TRUE "
					+ "  AND cc IN ("
					+ "    SELECT idStudent"
					+ "    FROM studentcourses"
					+ "    WHERE idCourse = ? AND"
					+ "          status = ?"
					+ "    )";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setInt(2,idCourse);
			statement.setString(3,"ENRROLLED");
			result = statement.executeQuery();
			while (result.next()) {
				Student student = new Student();
				student.setCc(result.getLong("cc"));
				student.setName(result.getString("name"));
				student.setEmail(result.getString("email"));
				student.setCellphone(result.getLong("cellphone"));
				student.setAddress(result.getString("address"));
				student.setSemester(result.getInt("semester"));
				student.setCreditsNumber(result.getInt("creditsNumber"));
				student.setFaculty(result.getString("faculty"));
				student.setActive(result.getBoolean("active"));
				students.add(student);
			}
			
			connection.desconectar();
			return students;
			
		} catch (SQLException e) {
			System.out.println("Error to get students: "+e.getMessage());
		}
		
		return students;
	}
	
}
