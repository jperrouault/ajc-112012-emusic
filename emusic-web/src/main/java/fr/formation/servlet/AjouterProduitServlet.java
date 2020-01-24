package fr.formation.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;


//@WebServlet("/ajouterProduit")
public class AjouterProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext() //ON AFFICHE LE FORMULAIRE
			.getRequestDispatcher("/WEB-INF/views/formProduit.jsp")
			.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Produit monProduit = new Produit();
		
		try {
			monProduit.setLibelle( req.getParameter("libelle") );
			monProduit.setPrix( new BigDecimal(req.getParameter("prix")) );
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		IDAOProduit daoProduit = (IDAOProduit)this.getServletContext().getAttribute("daoProduit");
		daoProduit.save(monProduit);
		resp.sendRedirect("listeProduits");
	}
}