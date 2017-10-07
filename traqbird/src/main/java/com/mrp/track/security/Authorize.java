package com.mrp.track.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Pattern;

import com.mrp.track.database.PGConnection;

public class Authorize {

	public User isValid(String username, String password)
	{
		User user = new User(username,password);
		try
		{
			Connection conn = PGConnection.getConnection();
			//check in table for the username and password
			PreparedStatement stmt = conn.prepareStatement("select * from admin_details where email = ? and password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				user.setOrgid(rs.getString("org_id"));
				user.setName(rs.getString("name"));
				user.setVerified(rs.getInt("verified"));
				user.setValid(true);
				return user;
			}
			else
			{
				user.setError("User details not found");
				return user;
			}

		} catch (SQLException e) {
			user.setError(e.getMessage());
			return user;
		}
	}

	public User isValid(String basicAuth)
	{
		String details = basicAuth.split(Pattern.quote(" "))[1];
		byte[] decoded = Base64.getDecoder().decode(details);
		details = decoded.toString();

		return isValid(details.split(Pattern.quote(":"))[0], basicAuth);
	}


}
