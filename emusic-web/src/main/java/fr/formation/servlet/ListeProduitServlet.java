package fr.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.dao.IDAOProduit;


//@WebServlet("/listeProduits")
public class ListeProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IDAOProduit daoProduit = (IDAOProduit)this.getServletContext().getAttribute("daoProduit");
		
		req.setAttribute("produits", daoProduit.findAll());
		
		this.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/listeProduits.jsp")
			.forward(req, resp);
	}
}