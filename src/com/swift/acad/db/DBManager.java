package com.swift.acad.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	final String dbUsername="root";
	final String dbPassword="Tolinator69";
	final String dbUri="jdbc:mysql://localhost:3306/webproject";
	private static DBManager instance = new DBManager();
	private Connection con;
	
	public static DBManager getInstance() {
		return instance;
	}
	
	public Connection open() throws SQLException, ClassNotFoundException{
/*		if (con != null){
			close();
		}*/
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbUri, dbUsername, dbPassword);
		return con;
	}
	
	public void close() throws SQLException{
		if (con !=null){
			con.close();
			con = null;
		}
			
	}

}
