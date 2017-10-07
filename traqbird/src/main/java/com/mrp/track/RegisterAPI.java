package com.mrp.track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mrp.track.communication.Mail;
import com.mrp.track.database.PGConnection;
import com.mrp.track.security.Authorize;
import com.mrp.track.security.User;

/**
 * Servlet implementation class Traq
 */
public class RegisterAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Authorize auth;


	/**
	 * Default constructor. 
	 */
	public RegisterAPI() {

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(405);
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		out.print("This method is not supported");
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			if(request.getPathInfo().contains("neworg"))
			{
		
				BufferedReader reader = request.getReader();
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(reader);
				JSONObject jsonObject = (JSONObject) obj;
				String org_name =(String) jsonObject.get("org_name");
				String address =(String) jsonObject.get("address");
				String city =(String) jsonObject.get("city");
				String state =(String) jsonObject.get("state");
				String country =(String) jsonObject.get("country");
				String pincode =(String) jsonObject.get("pincode");
				String name =(String) jsonObject.get("name");
				String phone =(String) jsonObject.get("phone");
				String email =(String) jsonObject.get("email");
				String password =email + ":" + (String) jsonObject.get("password");
				byte[] bytes = password.getBytes("UTF-8");
				String encoded = Base64.getEncoder().encodeToString(bytes);
				Connection conn = PGConnection.getConnection();
				if(conn!=null)
				{
				Random rnd = new Random();
				int n = 10000 + rnd.nextInt(90000);
				String org_id = "";
				if(org_name!=null)
				{
					if(org_name.length()>5)
						org_id = org_name.substring(0, 5) + n;
					else
						org_id = org_name + n;
				}
				
				
				PreparedStatement stmt = conn.prepareStatement("insert into org_details values(?,?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, org_id);
				stmt.setString(2, org_name);
				stmt.setString(3, address);
				stmt.setString(4, city);
				stmt.setString(5, state);
				stmt.setString(6, country);
				stmt.setString(7, pincode);
				stmt.setString(8, name);
				stmt.setString(9, phone);
				stmt.setString(10, email);
				stmt.executeUpdate();

				stmt = conn.prepareStatement("insert into admin_details values(?,?,?,?,?,?)");
				stmt.setString(1, org_id);
				stmt.setString(2, name);
				stmt.setString(3, email);
				stmt.setString(4, phone);
				stmt.setString(5, encoded);
				stmt.setInt(6, 0);
				stmt.executeUpdate();

				n = 1000 + rnd.nextInt(9000);
				String url = "http://www.traqbird.com/registerapi/verify/"+email+"/"+n+"/";
				Mail mail = new Mail();
				mail.sendmail(url, email, name);
				
				response.setStatus(201);
				response.setContentType("text");
				PrintWriter out = response.getWriter();
				out.print("Activation link sent");
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
			if(request.getPathInfo().contains("verify"))
			{
				String splitstr[] = request.getPathInfo().split(Pattern.quote("/"));
				int len = splitstr.length;
				String otp = splitstr[len-1];
				String email = splitstr[len-2];
				Connection conn = PGConnection.getConnection();
				if(conn!=null)
				{
				PreparedStatement stmt = conn.prepareStatement("select * from otp where email = ? and otp = ?");
				stmt.setString(1, email);
				stmt.setString(2, otp);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					stmt = conn.prepareStatement("update admin_details set verified = 1 where email = ?");
					stmt.setString(1, email);
					response.setStatus(200);
					response.setContentType("text");
					PrintWriter out = response.getWriter();
					out.print("Successfully Authenticated. Please login now.");
					out.flush();
					response.sendRedirect("http://www.traqbird.com");
				}
				else
				{
					response.setStatus(401);
					response.setContentType("text");
					PrintWriter out = response.getWriter();
					out.print("not valid otp for the email");
					out.flush();
				}
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
