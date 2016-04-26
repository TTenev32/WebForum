package com.swift.acad.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swift.acad.db.DBManager;



/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterPage")
public class RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
 
		String user_name = request.getParameter("loguser_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		try {
			Connection con = DBManager.getInstance().open();
			
			
			PreparedStatement ps = con.prepareStatement("insert into users (user_name, password, email) values (?, ?, ?)");
			
			ps.setString(1, user_name);
			ps.setString(2, password);
			ps.setString(3, email);
			int i=ps.executeUpdate();
			
			if (i >0){
				System.out.println("Success!");
				response.sendRedirect("Forums.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
