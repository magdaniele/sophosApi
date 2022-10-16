package sophosCollege.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.connection;
import sophosCollege.classes.Course;

public class courseService {

	public static ArrayList<Course> getAllCourses(long nit ) {
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

	public static String handleCourse(Course course, long collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `course` "
					+ "    ("
					+ "        `id`,"
					+ "        `name`,"
					+ "        `prerequisite`,"
					+ "        `nCredits`,"
					+ "        `availableSpaces`,"
					+ "        `idTeacher`,"
					+ "        `active`,"
					+ "        `collegeId`"
					+ "    ) "
					+ "VALUES (?,?,?,?,?,?,?,?)"
					+ "ON DUPLICATE KEY"
					+ "    UPDATE `name` = values(`name`),"
					+ "            `prerequisite` = values(`prerequisite`),"
					+ "            `nCredits` = values(`nCredits`) ,"
					+ "            `availableSpaces` = values(`availableSpaces`),"
					+ "            `idTeacher` = values(`idTeacher`) ,"
					+ "            `active` = VALUES(`active`),"
					+ "            `collegeId` = VALUES(`collegeId`)";
			
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
			result.next();
			course.setId(result.getInt("id"));
			course.setName(result.getString("name"));
			course.setPrerequisite(result.getInt("prerequisite"));
			course.setAvailableSpaces(result.getInt("availableSpaces"));
			course.setnCredits(result.getInt("nCredits"));
			course.setTeacher(result.getInt("idTeacher"));
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

	public static Course findEnrrolledCourse(long nit, long ccStudent, int prerequisite, String status) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Course course = null;
		
		try {

			String query = "	SELECT * "
							+ " FROM `studentcourses` "
							+ " WHERE collegeId = ? "
							+ " AND idCourse = ? "
							+ " AND idStudent = ? "
							+ " AND status = ?";
			
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setLong(2, prerequisite);
			statement.setLong(3, ccStudent);
			statement.setString(4, status);
			result = statement.executeQuery();
			if(result.next()) {	
				course = findCourse(nit, result.getInt("idCourse"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error finding course of prerequisite : "+e.getMessage());
		}
		
		return course;	
	}

	public static ArrayList<Course> findCourseByStatus(long nit, long idStudent, String status) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {

			String query = "SELECT *"
					+ "FROM `course`"
					+ "WHERE collegeId = ?"
					+ "  AND active = TRUE"
					+ "  AND id IN ("
					+ "    SELECT idCourse"
					+ "    FROM studentcourses"
					+ "    WHERE idStudent = ? AND"
					+ "          status = ?"
					+ "    )";
			statement = conn.prepareStatement(query);
			statement.setLong(1, nit);
			statement.setLong(2, idStudent);
			statement.setString(3, status);
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

}
