package fr.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Produit monProduit = (Produit)req.getSession().getAttribute("produit");
//		req.setAttribute("produit", monProduit);
		
		
		this.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/produit.jsp")
			.forward(req, resp);
	}
}