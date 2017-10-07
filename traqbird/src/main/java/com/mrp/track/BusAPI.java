package com.mrp.track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mrp.track.database.PGConnection;
import com.mrp.track.security.Authorize;
import com.mrp.track.security.User;

/**
 * Servlet implementation class Traq
 */
public class BusAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Authorize auth;


	/**
	 * Default constructor. 
	 */
	public BusAPI() {

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
			User user = doAuthorize(request);
			if(user.getValid())
			{
				if(user.getVerified()==1)
				{
					if(request.getPathInfo().contains("getlist"))
					{
						BufferedReader reader = request.getReader();
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(reader);
						JSONObject jsonObject = (JSONObject) obj;
						String org_id =(String) jsonObject.get("org_id");
						Connection conn = PGConnection.getConnection();
						if(conn!=null)
						{
							PreparedStatement stmt = conn.prepareStatement("select * from bus_details where org_id = ?");
							stmt.setString(1, org_id);
							ResultSet rs = stmt.executeQuery();
							JSONObject jsonResponse = new JSONObject();	
							JSONArray data = new JSONArray();
							data = JSON.convert(rs);
							jsonResponse.put("buslist", data);
							response.setStatus(200);
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(jsonResponse);
							out.flush();
						}
					}
				}
				else
				{
					response.setStatus(401);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print("Activation pending");
					out.flush();
				}
			}
			else
			{
				response.setStatus(401);
				response.setContentType("text");
				PrintWriter out = response.getWriter();
				out.print(user.getError());
				out.flush();
			}
		}
		catch(Exception e)
		{
			response.setStatus(500);
			response.setContentType("text");
			PrintWriter out = response.getWriter();
			out.print("Authentication Error");
			out.flush();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			User user = doAuthorize(request);
			if(user.getValid())
			{
				if(user.getVerified()==1)
				{
					if(request.getPathInfo().contains("addbus"))
					{
						BufferedReader reader = request.getReader();
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(reader);
						JSONObject jsonObject = (JSONObject) obj;
						String org_id =(String) jsonObject.get("org_id");
						String bus_id =(String) jsonObject.get("bus_id");
						String reg_no =(String) jsonObject.get("reg_no");
						String make =(String) jsonObject.get("make");
						int capacity =(Integer) jsonObject.get("capacity");
						int available =(Integer) jsonObject.get("available");

						Connection conn = PGConnection.getConnection();
						if(conn!=null)
						{

							PreparedStatement stmt = conn.prepareStatement("insert into bus_details values(?,?,?,?,?,?)");
							stmt.setString(1, org_id);
							stmt.setString(2, bus_id);
							stmt.setString(3, reg_no);
							stmt.setString(4, make);
							stmt.setInt(5, capacity);
							stmt.setInt(6, available);
							stmt.executeUpdate();
						}
					}
				}
				else
				{
					response.setStatus(401);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print("Activation pending");
					out.flush();
				}
			}
			else
			{
				response.setStatus(401);
				response.setContentType("text");
				PrintWriter out = response.getWriter();
				out.print(user.getError());
				out.flush();
			}
		}
		catch(Exception e)
		{
			response.setStatus(500);
			response.setContentType("text");
			PrintWriter out = response.getWriter();
			out.print("Authentication Error");
			out.flush();
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			User user = doAuthorize(request);
			if(user.getValid())
			{
				if(user.getVerified()==1)
				{
					if(request.getPathInfo().contains("edit"))
					{
						BufferedReader reader = request.getReader();
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(reader);
						JSONObject jsonObject = (JSONObject) obj;
						String org_id =(String) jsonObject.get("org_id");
						String bus_id =(String) jsonObject.get("bus_id");
						String reg_no =(String) jsonObject.get("reg_no");
						String make =(String) jsonObject.get("make");
						int capacity =(Integer) jsonObject.get("capacity");
						int available =(Integer) jsonObject.get("available");

						Connection conn = PGConnection.getConnection();
						if(conn!=null)
						{

							PreparedStatement stmt = conn.prepareStatement("update bus_details set(reg_no,make,capacity) = (?,?,?,?,?,?) where org_id = ? and bus_id = ?");
							stmt.setString(1, reg_no);
							stmt.setString(2, make);
							stmt.setInt(3, capacity);
							stmt.setString(4, org_id);
							stmt.setString(5, bus_id);
							stmt.executeUpdate();
						}
					}
				}
				else
				{
					response.setStatus(401);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print("Activation pending");
					out.flush();
				}
			}
			else
			{
				response.setStatus(401);
				response.setContentType("text");
				PrintWriter out = response.getWriter();
				out.print(user.getError());
				out.flush();
			}
		}
		catch(Exception e)
		{
			response.setStatus(500);
			response.setContentType("text");
			PrintWriter out = response.getWriter();
			out.print("Authentication Error");
			out.flush();
		}
	}

	
}
