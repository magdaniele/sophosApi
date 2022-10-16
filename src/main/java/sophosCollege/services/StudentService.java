package sophosCollege.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.connection;
import sophosCollege.classes.Student;

public class studentService {


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

	public static String handleStudent(Student student, long collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `student`"
					+ "     ("
					+ "     `cc`,"
					+ "      `name`,"
					+ "      `email`,"
					+ "      `cellphone`,"
					+ "      `address`,"
					+ "      `semester`,"
					+ "      `creditsNumber`,"
					+ "      `active`,"
					+ "      `collegeId`"
					+ "      ,`faculty`"
					+"		) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)"
					+ "ON DUPLICATE KEY "
					+ "		UPDATE `name` = values(`name`), "
					+ "            `email` = values(`email`), "
					+ "            `cellphone` = values(`cellphone`), "
					+ "            `address` = values(`address`), "
					+ "            `semester` = values(`semester`), "
					+ "            `creditsNumber` = values(`creditsNumber`), "
					+ "            `active` = VALUES(`active`), "
					+ "            `collegeId` = VALUES(`collegeId`), "
					+ "            `faculty` = VALUES(`faculty`)";
			
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
