package fr.formation.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;


//@WebServlet("/editerProduit")
public class EditerProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IDAOProduit daoProduit = (IDAOProduit)this.getServletContext().getAttribute("daoProduit");
		int id = Integer.parseInt(req.getParameter("id"));
		
		Produit leProduitAModifier = daoProduit.findById(id).orElse(null);
		
		req.setAttribute("produit", leProduitAModifier);
		
		this.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/formProduit.jsp")
			.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IDAOProduit daoProduit = (IDAOProduit)this.getServletContext().getAttribute("daoProduit");
		int id = Integer.parseInt(req.getParameter("id"));
		
		Produit leProduitAModifier = daoProduit.findById(id).orElse(null);
		
		try {
			leProduitAModifier.setLibelle( req.getParameter("libelle") );
			leProduitAModifier.setPrix( new BigDecimal(req.getParameter("prix")) );
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		daoProduit.save(leProduitAModifier);
		resp.sendRedirect("listeProduits");
	}
}