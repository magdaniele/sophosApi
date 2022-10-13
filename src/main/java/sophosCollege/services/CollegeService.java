package sophosCollege.services;
import java.sql.*;
import java.util.ArrayList;

import connection.connection;
import sophosCollege.College;
import sophosCollege.Course;
import sophosCollege.Student;
import sophosCollege.Teacher;


public class CollegeService {

	public static String addNewCollege(College college) {
		
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String consulta = "INSERT INTO `college` (`name`,`nit`,`city`,`country`) VALUES (?,?,?,?)";
			
			statement = conn.prepareStatement(consulta);
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
	
	public static ArrayList<Teacher> getAllTeachers(int nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ArrayList<Teacher> teachers = new ArrayList<Teacher>();;
		
		try {

			String consulta = "SELECT * FROM `teacher` WHERE collegeid = ? AND active = TRUE";
			
			statement = conn.prepareStatement(consulta);
			statement.setInt(1, nit);
			result = statement.executeQuery();
			while (result.next()) {
				Teacher teacher = new Teacher();
				teacher.setCc(result.getInt("cc"));
				teacher.setName(result.getString("name"));
				teacher.setEmail(result.getString("email"));
				teacher.setCellphone(result.getInt("cellphone"));
				teacher.setAddress(result.getString("address"));
				teacher.setDegree(result.getString("degree"));
				teacher.setYearsOfExperience(result.getInt("yearsOfExperience"));
				teacher.setActive(result.getBoolean("active"));
				teachers.add(teacher);
			}
			
			connection.desconectar();
			return teachers;
			
		} catch (SQLException e) {
			System.out.println("Error to get teachers: "+e.getMessage());
		}
		
		return teachers;
		
	}
	
	public static ArrayList<Student> getAllStudents(int nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ArrayList<Student> students = new ArrayList<Student>();;
		
		try {

			String consulta = "SELECT * FROM `student` WHERE collegeid = ? AND active = TRUE";
			
			statement = conn.prepareStatement(consulta);
			statement.setInt(1, nit);
			result = statement.executeQuery();
			while (result.next()) {
				Student student = new Student();
				student.setCc(result.getInt("cc"));
				student.setName(result.getString("name"));
				student.setEmail(result.getString("email"));
				student.setCellphone(result.getInt("cellphone"));
				student.setAddress(result.getString("address"));
				student.setSemester(result.getInt("semeter"));
				student.setCreditsNumber(result.getInt("creditsNumber"));
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
	
	public static ArrayList<Course> getAllCourses(int nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<Course> courses = new ArrayList<Course>();;
		try {

			String consulta = "SELECT* FROM `course` WHERE collegeid = ? AND active = TRUE";
			statement = conn.prepareStatement(consulta);
			statement.setInt(1, nit);
			result = statement.executeQuery();
			while (result.next()) {
				Course course = new Course();
				course.setId(result.getInt("id"));
				course.setName(result.getString("name"));
				course.setPrerequisite(result.getInt("prerequisite"));
				course.setAvailableSpaces(result.getInt("availableSpaces"));
				course.setAvailableSpaces(result.getInt("idTeacher"));
				course.setActive(result.getBoolean("active"));
				courses.add(course);
		}
		
		connection.desconectar();
		return courses;
		
		} catch (SQLException e) {
			System.out.println("Error to get courses: "+e.getMessage());
		}
		return courses;
	}

	public static String addNewTeacher(Teacher teacher, int collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String consulta = "INSERT INTO `teacher` "
					+ "(`cc`,`name`,`email`,`cellphone`,`address`,`degree`,`yearsOfExperience`,`active`,`collegeid`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			statement = conn.prepareStatement(consulta);
			statement.setInt(1, teacher.getCc());
			statement.setString(2, teacher.getName());
			statement.setString(3, teacher.getEmail());
			statement.setInt(4, teacher.getCellphone());
			statement.setString(5, teacher.getAddress());
			statement.setString(6, teacher.getDegree());
			statement.setInt(7, teacher.getYearsOfExperience());
			statement.setBoolean(8, teacher.isActive());
			statement.setInt(9, collegeId);
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
		}
		
		return result;
	}

	public static String addNewStudent(Student student, int collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String consulta = "INSERT INTO `student` "
					+ "(`cc`,`name`,`email`,`cellphone`,`address`,`semester`,`creditsNumber`,`active`,`collegeid`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			statement = conn.prepareStatement(consulta);
			statement.setInt(1, student.getCc());
			statement.setString(2, student.getName());
			statement.setString(3, student.getEmail());
			statement.setInt(4, student.getCellphone());
			statement.setString(5, student.getAddress());
			statement.setInt(6, student.getSemester());
			statement.setInt(7, student.getCreditsNumber());
			statement.setBoolean(8, student.isActive());
			statement.setInt(9, collegeId);
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to create student: "+e.getMessage());
		}
		
		return result;
	}
	
	public static String addNewCourse(Course course, int collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String consulta = "INSERT INTO `course` "
					+ "(`id`,`name`,`prerequisite`,`nCredits`,`availableSpaces`,`idTeacher`,`active`,`collegeId`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			statement = conn.prepareStatement(consulta);
			statement.setInt(1, course.getId());
			statement.setString(2, course.getName());
			statement.setInt(3, course.getPrerequisite());
			statement.setInt(4, course.getnCredits());
			statement.setInt(5, course.getAvailableSpaces());
			statement.setInt(6, course.getIdTeacher());
			statement.setBoolean(7, course.isActive());
			statement.setInt(9, collegeId);
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to create course: "+e.getMessage());
		}
		
		return result;
	}
	
//	private void findTeacher() {
//		
//	}
//
//	private void findStudent() {
//		
//	}
//	
//	private void findCourse() {
//		
//	}
//
//	private void DeleteTeacher() {
//		
//	}
//
//	private void DeleteStudent() {
//		
//	}
//	
//	private void DeleteCourse() {
//		
//	}

}
