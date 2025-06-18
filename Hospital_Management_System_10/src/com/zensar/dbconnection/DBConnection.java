package com.zensar.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getDBConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Driver class name applicable for mysql version
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zensarproject?useSSL=false","root","root");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}return con;
	}
}
