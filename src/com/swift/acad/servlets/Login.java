package com.swift.acad.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swift.acad.db.UserDAO;
import com.swift.acad.entities.LoginStatus;
import com.swift.acad.entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	static User loginUser;
	static UserDAO login;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("loguser_name");
		String password = request.getParameter("logpassword");
		
		HttpSession session = request.getSession();
		
		if (session.isNew()) {
			session.setAttribute("logged_status", LoginStatus.NOT_LOGGED_IN);
		}
		loginUser = new User(username, password);
		login = new UserDAO();
		try {
			System.out.println(login.authentiate(loginUser));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (login.authentiate(loginUser)) {
				session.setAttribute("logged_status", LoginStatus.LOGGED_IN);
				try {
					session.setAttribute("loguser_name", login.getUser(username));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Logged in baby!");
				response.sendRedirect("Forums.jsp");
			} else {
				response.sendRedirect("index.jsp");
				System.out.println("Try again");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
