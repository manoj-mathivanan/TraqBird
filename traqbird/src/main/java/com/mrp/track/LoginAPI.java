package com.mrp.track;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrp.track.database.PGConnection;
import com.mrp.track.security.Authorize;
import com.mrp.track.security.User;

/**
 * Servlet implementation class Traq
 */
public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Authorize auth;

	/**
	 * Default constructor. 
	 */
	public LoginAPI() {

	}

	public void init() throws ServletException {
		auth = new Authorize();
		PGConnection.getConnection();
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
		try
		{
			User user = doAuthorize(request);
			if(user.getValid())
			{
				if(user.getVerified()==1)
				{
					response.setStatus(200);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print("{\"org_id\":\"" + user.getOrgid() + "\"name\":\"" + user.getName() + "\"}");
					out.flush();
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
		
	}

}
