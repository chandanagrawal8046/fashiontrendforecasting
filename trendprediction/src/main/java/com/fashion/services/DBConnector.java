package com.fashion.services;
import java.sql.*;

public class DBConnector 
{
	//static String password="volkswagen";
	static String password="sharayu";
	
	public static Connection connectDB()
	{
		Connection con=null;
	
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fashiontrendsdb?user=root&password="+password);
	        System.out.println("Connected...");
		}
		catch(Exception ex) {}
		
		return con;  
	}	
}
