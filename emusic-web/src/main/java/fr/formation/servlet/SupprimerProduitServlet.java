package fr.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.dao.IDAOProduit;


//@WebServlet("/supprimerProduit")
public class SupprimerProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IDAOProduit daoProduit = (IDAOProduit)this.getServletContext().getAttribute("daoProduit");
		int id = Integer.parseInt(req.getParameter("id"));
		
		daoProduit.deleteById(id);
		resp.sendRedirect("listeProduits");
	}
}