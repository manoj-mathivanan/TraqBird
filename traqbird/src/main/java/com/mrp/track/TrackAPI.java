package com.mrp.track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mrp.track.database.PGConnection;
import com.mrp.track.security.Authorize;
import com.mrp.track.security.User;

/**
 * Servlet implementation class Traq
 */
public class TrackAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Authorize auth;


	/**
	 * Default constructor. 
	 */
	public TrackAPI() {

	}

	public void init() throws ServletException {
		auth = new Authorize();

	}

	public User doAuthorize(HttpServletRequest request)
	{
		String basicAuth = request.getHeader("Authorization");
		return auth.isValid(basicAuth);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if(request.getPathInfo().contains("getlive"))
					{
						String imei = request.getHeader("IMEI");
						Connection conn = PGConnection.getConnection();
						if(conn!=null)
						{
							PreparedStatement stmt = conn.prepareStatement("select * from tracker where gps_id = ?");
							stmt.setString(1, imei);
							ResultSet rs = stmt.executeQuery();
							JSONObject jsonResponse = new JSONObject();	
							JSONArray data = new JSONArray();
							data = JSON.convert(rs);
							jsonResponse.put("live_data", data);
							response.setStatus(200);
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(jsonResponse);
							out.flush();
						}else
						{
							response.setStatus(500);
							response.setContentType("text");
							PrintWriter out = response.getWriter();
							out.print("Database connection failed");
							out.flush();
						}

					}
					
				}
				catch(Exception e)
				{
					response.setStatus(500);
					response.setContentType("text");
					PrintWriter out = response.getWriter();
					out.print(e.getMessage());
					out.flush();
				}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			if(request.getPathInfo().contains("update"))
			{
		
				BufferedReader reader = request.getReader();
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(reader);
				JSONObject jsonObject = (JSONObject) obj;
				String imei =(String) jsonObject.get("IMEI");
				String lat =(String) jsonObject.get("LAT");
				String lon =(String) jsonObject.get("LON");
				Date dateTime = new Date();
				Connection conn = PGConnection.getConnection();
				if(conn!=null)
				{
				PreparedStatement stmt = conn.prepareStatement("update tracker set lat=?, lon=?, time=? where gps_id = ?");
				stmt.setString(4, imei);
				stmt.setString(1, lat);
				stmt.setString(2, lon);
				stmt.setTimestamp(3, new java.sql.Timestamp((dateTime).getTime()));
				stmt.executeUpdate();	
				response.setStatus(204);
				response.setContentType("text");
				PrintWriter out = response.getWriter();
				out.print("Update success");
				out.flush();
				}
				else
				{
					response.setStatus(500);
					response.setContentType("text");
					PrintWriter out = response.getWriter();
					out.print("Database connection failed");
					out.flush();
				}

			}
			
		}
		catch(Exception e)
		{
			response.setStatus(500);
			response.setContentType("text");
			PrintWriter out = response.getWriter();
			out.print(e.getMessage());
			out.flush();
		}
	}

}
