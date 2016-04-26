package com.swift.acad.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swift.acad.db.DBManager;
import com.swift.acad.db.UserDAO;
import com.swift.acad.entities.LoginStatus;
import com.swift.acad.entities.User;

/**
 * Servlet implementation class CreateMessage
 */
@WebServlet("/CreateSubforumMessage")
public class CreateSubforumMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSubforumMessage() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		
		HttpSession session = request.getSession();
		int threadId = Integer.parseInt(request.getParameter("thread_id"));
		User user =(User)session.getAttribute("loguser_name");
		String user_name = user.getUser_name();
		UserDAO usr = new UserDAO();
		

		
		if (session.isNew()) {
			session.setAttribute("logged_status", LoginStatus.NOT_LOGGED_IN);
			response.sendRedirect("index.jsp");
		}
		try {
			Connection con = DBManager.getInstance().open();
			int id = usr.getUser(user_name).getUser_id();
			PreparedStatement ps = con.prepareStatement("insert into messages (message, created_at, thread_id, user_id) values (?, ?, ?, ?)");

			ps.setString(1, message);
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			ps.setInt(3, threadId);
			ps.setInt(4, id);
			int i=ps.executeUpdate();
			
			if (i >0){
				System.out.println("Success!");
				request.getRequestDispatcher(request.getParameter("viewid")).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
