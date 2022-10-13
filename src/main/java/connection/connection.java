package connection;
import java.sql.*;

public class connection {
		private String nombreBd="sophoscollegedatabase";
		private String usuario="root";
		private String password="";
		private String url="jdbc:mysql://localhost:3306/"+nombreBd;

		Connection conn=null;
		public connection(){
			try {
				//getConnection
				conn=DriverManager.getConnection(url,usuario,password);
				if (conn!=null) {
					System.out.println("Conexion Exitosa  a la BD: "+nombreBd);
				}else{
					System.out.println("******************NO SE PUDO CONECTAR "+nombreBd);
				}
			
			} catch (SQLException e) {
				System.out.println("ocurre una SQLException: "+e.getMessage());
				System.out.println("Verifique que Mysql esté encendido...");
			}
		}
		public Connection getConnection(){
			return conn;
		}
		public void desconectar(){
			conn=null;
		}
	
}
