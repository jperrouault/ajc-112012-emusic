package fr.formation.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.formation.dao.IDAOProduit;
import fr.formation.exception.EmptyLibelleException;
import fr.formation.model.Produit;

public class DAOProduitSQL extends DAOConnexionSQL implements IDAOProduit {
	@Override
	public List<Produit> findAll() {
		List<Produit> mesProduits = new ArrayList<Produit>();
		
		try {
			if (connection != null) {
				Statement myStatement = connection.createStatement();
				ResultSet myResult = myStatement.executeQuery("SELECT * FROM produit p"
						+ "	LEFT JOIN fournisseur f ON f.FOU_ID = p.PRO_FOURNISSEUR_ID");

				while (myResult.next()) {
					try { // On met le try ici pour pas bloquer toute la liste
						mesProduits.add(em.get(myResult, Produit.class));
					}

					catch (EmptyLibelleException e) {
						System.out.println("libellé vide");
					}
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERR ...");
		}
		
		
		return mesProduits;
	}
	
	
	
	
	

	@Override
	public Produit findById(Integer id) {
		return null;
	}
	

	@Override
	public Produit save(Produit entity) {
		PreparedStatement myStatement = null;
		
		try {
			if (entity.getId() == 0) { //INSERT
				myStatement = connection.prepareStatement("INSERT INTO produit"
						+ "	(PRO_LIBELLE, PRO_PRIX, PRO_FOURNISSEUR_ID)"
						+ "	VALUES (?, ?, ?)");
				
				
				
				if (entity.getFournisseur().getId() == 0) { //NOUVEAU FOURNISSEUR
					PreparedStatement myStatementFournisseur = connection.prepareStatement("INSERT INTO fournisseur (FOU_SOCIETE) VALUES (?)");
					myStatementFournisseur.setString(1, entity.getFournisseur().getNom());
					myStatementFournisseur.execute();
					
					//On récupère l'identifiant pour l'affecter au fournisseur
					myStatementFournisseur = connection.prepareStatement("SELECT LAST_INSERT_ID()"); //OU SELECT FOU_ID FROM fournisseur ORDER BY FOU_ID DESC LIMIT 1
					ResultSet myResult = myStatementFournisseur.executeQuery();
					
					myResult.next();
					entity.getFournisseur().setId(myResult.getInt(1));
				}
	
				myStatement.setInt(3, entity.getFournisseur().getId());
			}
			
			else { //UPDATE
				myStatement = connection.prepareStatement("UPDATE produit"
						+ "	SET PRO_LIBELLE = ?, PRO_PRIX = ?"
						+ "	WHERE PRO_ID = ?");
	
				myStatement.setInt(3, entity.getId());
			}
	
			myStatement.setString(1, entity.getLibelle());
			myStatement.setBigDecimal(2, entity.getPrix());
			
			myStatement.execute();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	

	@Override
	public void delete(Produit entity) {
		this.deleteById(entity.getId());
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement myStatement = null;
		
		try {
			myStatement = connection.prepareStatement("DELETE FROM produit"
					+ "	WHERE PRO_ID = ?");
	
			myStatement.setInt(1, id);
			
			myStatement.execute();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public Optional<Produit> findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public List<Produit> findAllWithReclamation() {
		// TODO Auto-generated method stub
		return null;
	}

}