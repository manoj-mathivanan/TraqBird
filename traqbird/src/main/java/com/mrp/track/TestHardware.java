package com.mrp.track;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrp.track.communication.Mail;
import com.mrp.track.security.Authorize;
import com.mrp.track.security.User;

/**
 * Servlet implementation class Traq
 */
public class TestHardware extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Authorize auth;


	/**
	 * Default constructor. 
	 */
	public TestHardware() {

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
		Mail mail = new Mail();
		try{
				
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = reader.readLine()) != null) {
		    builder.append(aux);
		}

		String text = builder.toString();
		System.out.println(text);
		System.out.println(text + "____" + request.getPathInfo());
		//mail.sendmail(text, "ma.manoj@gmail.com", request.getPathInfo());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage() + "__error__" + request.getPathInfo());
			//mail.sendmail(e.getMessage(), "ma.manoj@gmail.com", "error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mail mail = new Mail();
		try{
				
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = reader.readLine()) != null) {
		    builder.append(aux);
		}

		String text = builder.toString();
		System.out.println(text);
		mail.sendmail(text, "ma.manoj@gmail.com", request.getPathInfo());
		}
		catch(Exception e)
		{
			mail.sendmail(e.getMessage(), "ma.manoj@gmail.com", "error");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mail mail = new Mail();
		try{
				
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = reader.readLine()) != null) {
		    builder.append(aux);
		}

		String text = builder.toString();
		System.out.println(text);
		mail.sendmail(text, "ma.manoj@gmail.com", request.getPathInfo());
		}
		catch(Exception e)
		{
			mail.sendmail(e.getMessage(), "ma.manoj@gmail.com", "error");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mail mail = new Mail();
		try{
				
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = reader.readLine()) != null) {
		    builder.append(aux);
		}

		String text = builder.toString();
		System.out.println(text);
		mail.sendmail(text, "ma.manoj@gmail.com", request.getPathInfo());
		}
		catch(Exception e)
		{
			mail.sendmail(e.getMessage(), "ma.manoj@gmail.com", "error");
		}
	}
}
