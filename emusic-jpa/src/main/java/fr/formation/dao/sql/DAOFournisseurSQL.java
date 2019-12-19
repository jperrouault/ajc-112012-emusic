package fr.formation.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.exception.EmptyLibelleException;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class DAOFournisseurSQL extends DAOConnexionSQL implements IDAOFournisseur {
	@Override
	public List<Fournisseur> findAll() {
		List<Fournisseur> mesFournisseurs = new ArrayList<Fournisseur>();
		
		try {
			if (connection != null) {
				Statement myStatement = connection.createStatement();
				ResultSet myResult = myStatement.executeQuery("SELECT * FROM fournisseur");

				while (myResult.next()) {
					try {
						mesFournisseurs.add(em.get(myResult, Fournisseur.class));
					}
					
					catch (EmptyLibelleException e) {
						e.printStackTrace();
					}
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERR ...");
		}
		
		
		return mesFournisseurs;
	}
	
	
	
	
	

	@Override
	public Fournisseur findById(Integer id) {
		return null;
	}
	

	@Override
	public Fournisseur save(Fournisseur entity) {
		PreparedStatement myStatement = null;
		
		try {
			if (entity.getId() == 0) { //INSERT
				myStatement = connection.prepareStatement("INSERT INTO fournisseur"
						+ "	(FOU_SOCIETE)"
						+ "	VALUES (?)");
			}
			
			else { //UPDATE
				myStatement = connection.prepareStatement("UPDATE fournisseur"
						+ "	SET FOU_SOCIETE = ?"
						+ "	WHERE FOU_ID = ?");
	
				myStatement.setInt(2, entity.getId());
			}
	
			myStatement.setString(1, entity.getNom());
			
			myStatement.execute();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	

	@Override
	public void delete(Fournisseur entity) {
		this.deleteById(entity.getId());
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement myStatement = null;
		
		try {
			myStatement = connection.prepareStatement("DELETE FROM fournisseur"
					+ "	WHERE FOU_ID = ?");
	
			myStatement.setInt(1, id);
			myStatement.execute();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}






	@Override
	public Fournisseur findByIdFetchingProduits(Integer id) {
		PreparedStatement myStatement = null;
		ResultSet myResult = null;
		Fournisseur myFournisseur = null;
		
		
		try {
			myStatement = connection.prepareStatement("SELECT * FROM fournisseur f"
					+ "	WHERE FOU_ID = ?");
	
			myStatement.setInt(1, id);
			myResult = myStatement.executeQuery();
			
			if (myResult.next()) {
				myFournisseur = em.get(myResult, Fournisseur.class);
				
	
				myStatement = connection.prepareStatement("SELECT * FROM produit p"
						+ "	WHERE PRO_FOURNISSEUR_ID = ?");
		
				myStatement.setInt(1, id);
				myResult = myStatement.executeQuery();
				
				while (myResult.next()) {
					myFournisseur.getProduits().add(em.get(myResult, Produit.class));
				}
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return myFournisseur;
	}






	@Override
	public List<Fournisseur> findByNomContaining(String nom) {
		List<Fournisseur> mesFournisseurs = new ArrayList<Fournisseur>();
		
		try {
			if (connection != null) {
				PreparedStatement myStatement = connection.prepareStatement("SELECT * FROM fournisseur WHERE FOU_SOCIETE LIKE ?");
				myStatement.setString(1, "%" + nom + "%");
				ResultSet myResult = myStatement.executeQuery();
				
				while (myResult.next()) {
					try {
						mesFournisseurs.add(em.get(myResult, Fournisseur.class));
					}
					
					catch (EmptyLibelleException e) {
						e.printStackTrace();
					}
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERR ...");
		}
		
		
		return mesFournisseurs;
	}
}