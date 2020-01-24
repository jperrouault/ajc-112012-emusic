package fr.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext() //ON AFFICHE LE FORMULAIRE
			.getRequestDispatcher("/WEB-INF/views/login.jsp")
			.forward(req, resp);
		
		//ET ON NE FAIT QUE CA !
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myUsername = req.getParameter("username");
		
		req.getSession().setAttribute("utilisateur", myUsername);
		resp.sendRedirect("home");
	}
}