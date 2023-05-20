package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static String bd="biblioteca";
	private static String parametros="?useSSL=false&serverTimezone=UTC";
	private static String user="root";
	private static String pass="1234";
	private static String url="jdbc:mysql://localhost:3309/"+bd+parametros;
	private Connection conn;
	
	public DbConnection() throws SQLException {
		// TODO Auto-generated constructor stub
		
			conn=DriverManager.getConnection(url,user,pass);
			System.out.println("La conexion se realizo con exito");
		
	}

	public Connection getConnection() {
		return conn;
	}
	public void disconect() throws SQLException {
		if(conn!=null) {
			System.out.println("Cerrando BBDD");
			conn.close();
			System.out.println("BBDD desconectada");
		}
	}

}
