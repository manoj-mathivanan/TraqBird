package com.mrp.track.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PGConnection {
	
	public static Connection conn;
	
	public static Connection getConnection()
	{
		if(conn!=null)
		{
			return conn;
		}
		else
		{
			try {
				conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-204-35-207.compute-1.amazonaws.com:5432/dd6ju59jfmte9i?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory","uueitmrbwrmjxs","JXMIqwB_UdWJD1-YSOnvzxXyCa");
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
		}
	}

}
