package fr.formation.dao.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.formation.exception.EmptyLibelleException;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class EntityManager {
	private List<Produit> produits = new ArrayList<Produit>();
	private List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
	
	
	
	public Fournisseur getFournisseur(ResultSet result) throws SQLException {
		try {
			int id = result.getInt("FOU_ID");
			
			//Existe-t-il ?
			for (Fournisseur f : this.fournisseurs) {
				if (f.getId() == id) {
					return f;
				}
			}
			
			//Je suis arrivé là, DONC : le fournisseur n'existe pas
			Fournisseur monFournisseur = new Fournisseur();
			
			//On affecte les infos du fournisseur
			monFournisseur.setId(result.getInt("FOU_ID"));
			monFournisseur.setNom(result.getString("FOU_SOCIETE"));
		
			this.fournisseurs.add(monFournisseur);
			return monFournisseur;
		}
		
		catch (SQLException e) {
			return null;
		}
	}
	

	
	public Produit getProduit(ResultSet result) throws SQLException, EmptyLibelleException {
		int id = result.getInt("PRO_ID");
		
		//Existe-t-il ?
		for (Produit p : this.produits) {
			if (p.getId() == id) {
				return p;
			}
		}
		
		//Je suis arrivé là, DONC : le produit n'existe pas
		Produit monProduit = new Produit();
		Fournisseur monFournisseur = this.getFournisseur(result);
		
		// On affecte l'id ... qu'on récupère du resultSet
		monProduit.setId(result.getInt("PRO_ID"));
		monProduit.setLibelle(result.getString("PRO_LIBELLE"));
		monProduit.setPrix(result.getBigDecimal("PRO_PRIX"));
		monProduit.setFournisseur(monFournisseur);
		
		this.produits.add(monProduit);
		return monProduit;
	}
	
	
	
	public <T> T get(ResultSet result, Class<T> cls) throws SQLException, EmptyLibelleException {
		if (cls == Produit.class) {
			return (T) this.getProduit(result);
		}
		
		else {
			return (T) this.getFournisseur(result);
		}
		
	}
}