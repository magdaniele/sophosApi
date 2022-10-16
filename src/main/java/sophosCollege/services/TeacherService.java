package sophosCollege.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.connection;
import sophosCollege.classes.Teacher;

public class teacherService {

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
	
	public static String handleTeacher(Teacher teacher, long collegeId) {
		connection connection = new connection();
		Connection conn =  connection.getConnection();
		PreparedStatement statement = null;
		String result = "";
		
		try {

			String query = "INSERT INTO `teacher`"
					+ "    ("
					+ "        `cc`,"
					+ "       `name`,"
					+ "       `email`,"
					+ "       `cellphone`,"
					+ "       `address`,"
					+ "       `degree`,"
					+ "       `yearsOfExperience`,"
					+ "       `active`,"
					+ "       `collegeId`"
					+ "    )"
					+ "VALUES (?,?,?,?,?,?,?,?,?)"
					+ "ON DUPLICATE KEY"
					+ "    UPDATE `name` = values(`name`),"
					+ "            `email` = values(`email`),"
					+ "            `cellphone` = values(`cellphone`) ,"
					+ "            `address` = values(`address`),"
					+ "            `degree` = values(`degree`) ,"
					+ "            `yearsOfExperience` = values(`yearsOfExperience`),"
					+ "            `active` = VALUES(`active`),"
					+ "            `collegeId` = VALUES(`collegeId`)";
			
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

}
