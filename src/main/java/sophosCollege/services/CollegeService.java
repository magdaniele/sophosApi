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

	public static ArrayList<Course> getAllCourses(long nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {

			String query = "SELECT* FROM `course` WHERE collegeId = ? AND active = TRUE";
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
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

	public static ArrayList<Teacher> getAllTeachers(long nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try {

			String query = "SELECT * FROM `teacher` WHERE collegeId = ? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			result = statement.executeQuery();
			while (result.next()) {
				Teacher teacher = new Teacher();
				teacher.setCc(result.getInt("cc"));
				teacher.setName(result.getString("name"));
				teacher.setEmail(result.getString("email"));
				teacher.setCellphone(result.getLong("cellphone"));
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
	
	public static ArrayList<Student> getAllStudents(long nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ArrayList<Student> students = new ArrayList<Student>();
		
		try {

			String query = "SELECT * FROM `student` WHERE collegeId = ? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
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
	
	public static String addNewCourse(Course course, long collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `course` "
					+ "(`id`,`name`,`prerequisite`,`nCredits`,`availableSpaces`,`idTeacher`,`active`,`collegeId`) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			
			statement = conn.prepareStatement(query);
			statement.setInt(1, course.getId());
			statement.setString(2, course.getName());
			statement.setInt(3, course.getPrerequisite());
			statement.setInt(4, course.getnCredits());
			statement.setInt(5, course.getAvailableSpaces());
			statement.setInt(6, course.getIdTeacher());
			statement.setBoolean(7, course.getActive());
			statement.setLong(8, collegeId);
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to create course: "+e.getMessage());
		}
		
		return result;
	}
	
	public static String addNewTeacher(Teacher teacher, long collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `teacher` "
					+ "(`cc`,`name`,`email`,`cellphone`,`address`,`degree`,`yearsOfExperience`,`active`,`collegeId`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, teacher.getCc());
			statement.setString(2, teacher.getName());
			statement.setString(3, teacher.getEmail());
			statement.setLong(4, teacher.getCellphone());
			statement.setString(5, teacher.getAddress());
			statement.setString(6, teacher.getDegree());
			statement.setInt(7, teacher.getYearsOfExperience());
			statement.setBoolean(8, teacher.getActive());
			statement.setLong(9, collegeId);
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to create teacher: "+e.getMessage());
		}
		
		return result;
	}

	public static String addNewStudent(Student student, long collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `student` "
					+ "(`cc`,`name`,`email`,`cellphone`,`address`,`semester`,`creditsNumber`,`active`,`collegeId`,`faculty`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, student.getCc());
			statement.setString(2, student.getName());
			statement.setString(3, student.getEmail());
			statement.setLong(4, student.getCellphone());
			statement.setString(5, student.getAddress());
			statement.setInt(6, student.getSemester());
			statement.setInt(7, student.getCreditsNumber());
			statement.setBoolean(8, student.getActive());
			statement.setLong(9, collegeId);
			statement.setString(10, student.getFaculty());
			statement.execute();
			
			result = "Registro Exitoso!!!";
			
			connection.desconectar();
			
		} catch (SQLException e) {
			System.out.println("Error to create student: "+e.getMessage());
		}
		
		return result;
	}

	public static Course findCourse(long nit, int id) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Course course = new Course();
		
		try {

			String query = "SELECT * FROM `course` WHERE collegeId = ? AND id = ? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setInt(2,id);
			result = statement.executeQuery();
			course.setId(result.getInt("id"));
			course.setName(result.getString("name"));
			course.setPrerequisite(result.getInt("prerequisite"));
			course.setAvailableSpaces(result.getInt("availableSpaces"));
			course.setAvailableSpaces(result.getInt("idTeacher"));
			course.setActive(result.getBoolean("active"));

			
		} catch (SQLException e) {
			System.out.println("Error finding course: "+e.getMessage());
		}
		
		return course;	
	}
	
	public static Course findCourseByName(long nit, String name) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Course course = new Course();
		
		try {

			String query = "SELECT * FROM `course` WHERE collegeId = ? AND name = ? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setString(2,name);
			result = statement.executeQuery();
			course.setId(result.getInt("id"));
			course.setName(result.getString("name"));
			course.setPrerequisite(result.getInt("prerequisite"));
			course.setAvailableSpaces(result.getInt("availableSpaces"));
			course.setAvailableSpaces(result.getInt("idTeacher"));
			course.setActive(result.getBoolean("active"));

			
		} catch (SQLException e) {
			System.out.println("Error finding course named "+name+": "+e.getMessage());
		}
		
		return course;	
	}
	
	public static ArrayList<Course> findCourseBySpace(long nit) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<Course> courses = new ArrayList<Course>();
		
		try {

			String query = "SELECT * FROM `course` WHERE collegeId = ? AND active = TRUE AND availableSpaces>0";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
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
			System.out.println("Error to get courses with available spaces: "+e.getMessage());
		}
		return courses;
	}

	public static Teacher findTeacher(long nit, long cc) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Teacher teacher = new Teacher();
		
		try {

			String query = "SELECT * FROM `teacher` WHERE collegeId = ? AND cc = ? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setLong(2,cc);
			result = statement.executeQuery();
			teacher.setCc(result.getInt("cc"));
			teacher.setName(result.getString("name"));
			teacher.setEmail(result.getString("email"));
			teacher.setCellphone(result.getInt("cellphone"));
			teacher.setAddress(result.getString("address"));
			teacher.setDegree(result.getString("degree"));
			teacher.setYearsOfExperience(result.getInt("yearsOfExperience"));
			teacher.setActive(result.getBoolean("active"));
			
		} catch (SQLException e) {
			System.out.println("Error finding teacher: "+e.getMessage());
		}
		
		return teacher;		
	}
	
	public static Teacher findTeacherByName(long nit, String name) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Teacher teacher = new Teacher();
		
		try {

			String query = "SELECT * FROM `teacher` WHERE collegeId = ? AND name = ? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setString(2, name);
			result = statement.executeQuery();
			teacher.setCc(result.getInt("cc"));
			teacher.setName(result.getString("name"));
			teacher.setEmail(result.getString("email"));
			teacher.setCellphone(result.getInt("cellphone"));
			teacher.setAddress(result.getString("address"));
			teacher.setDegree(result.getString("degree"));
			teacher.setYearsOfExperience(result.getInt("yearsOfExperience"));
			teacher.setActive(result.getBoolean("active"));
			
		} catch (SQLException e) {
			System.out.println("Error finding teacher named "+name+": "+e.getMessage());
		}
		
		return teacher;		
	}

	public static Student findStudent(long nit, long cc) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Student student = new Student();
		
		try {

			String query = "SELECT * FROM `student` WHERE collegeId = ? AND cc = ? AND active= TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setLong(2,cc);
			result = statement.executeQuery();
			result.next();
			student.setCc(result.getLong("cc"));
			student.setName(result.getString("name"));
			student.setEmail(result.getString("email"));
			student.setCellphone(result.getLong("cellphone"));
			student.setAddress(result.getString("address"));
			student.setSemester(result.getInt("semester"));
			student.setCreditsNumber(result.getInt("creditsNumber"));
			student.setFaculty(result.getString("faculty"));
			student.setActive(result.getBoolean("active"));
			
		} catch (SQLException e) {
			System.out.println("Error finding student: "+e.getMessage());
		}
		
		return student;	
		
	}

	public static Student findStudentByName(long nit, String name) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Student student = new Student();
		
		try {

			String query = "SELECT * FROM `student` WHERE collegeId = ? AND name = ? AND active= TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setString(2,name);
			result = statement.executeQuery();
			result.next();
			student.setCc(result.getLong("cc"));
			student.setName(result.getString("name"));
			student.setEmail(result.getString("email"));
			student.setCellphone(result.getLong("cellphone"));
			student.setAddress(result.getString("address"));
			student.setSemester(result.getInt("semester"));
			student.setCreditsNumber(result.getInt("creditsNumber"));
			student.setFaculty(result.getString("faculty"));
			student.setActive(result.getBoolean("active"));
			
		} catch (SQLException e) {
			System.out.println("Error finding student named "+name+": "+e.getMessage());
		}
		
		return student;	
		
	}
	
	public static ArrayList<Student> findStudentsByFaculty(long nit, String faculty) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ArrayList<Student> students = new ArrayList<Student>();
		
		try {

			String query = "SELECT * FROM `student` WHERE collegeId = ? AND faculty=? AND active = TRUE";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setString(2, faculty);
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
			System.out.println("Error to get students with "+faculty+" faculty: "+e.getMessage());
		}
		
		return students;
	}
	
	public static String deleteCourse(long nit, int id) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			String result = "";
		
		try {

			String query = "UPDATE `course` SET active=? WHERE collegeId = ? AND id = ?";
			
			statement = conn.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setLong(2, nit);
			statement.setInt(3,id);
			statement.execute();
			
			result = "Course with ID: '"+id+"' is desactivated!";
			
		} catch (SQLException e) {
			System.out.println("Error desactivating course: "+e.getMessage());
		}
		
		return result;
		
	}

	public static String deleteTeacher(long nit, long cc) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			String result = "";
		
		try {

			String query = "UPDATE `teacher` SET active=? WHERE collegeId = ? AND cc = ?";
			
			statement = conn.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setLong(2, nit);
			statement.setLong(3,cc);
			statement.execute();
			
			result = "Teacher with ID: '"+cc+"' is desactivated!";
			
		} catch (SQLException e) {
			System.out.println("Error desactivating teacher: "+e.getMessage());
		}
		
		return result;		
	}

	public static String deleteStudent(long nit, long cc) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
			PreparedStatement statement = null;
			String result = "";
		
		try {

			String query = "UPDATE `student` SET active=? WHERE collegeid = ? AND cc = ?";
			
			statement = conn.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setLong(2, nit);
			statement.setLong(3,cc);
			statement.execute();
			
			result = "Student with ID: '"+cc+"' is desactivated!";
			
		} catch (SQLException e) {
			System.out.println("Error desactivating student: "+e.getMessage());
		}
		
		return result;
	}
	
}
