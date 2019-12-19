package fr.formation;

import java.math.BigDecimal;
import java.util.List;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.dao.IDAOProduit;
import fr.formation.dao.sql.DAOFournisseurSQL;
import fr.formation.dao.sql.DAOProduitSQL;
import fr.formation.exception.EmptyLibelleException;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class Application {
	public static void main(String[] args) {
//		Connection myConnection = null;
//		
//		System.out.println("DEBUT");
//		try {
//			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emusic?serverTimezone=UTC", "root", "");
//		}
//
//		catch (Exception e) {
//			System.out.println("Pb de connexion");
//		}
//
//		try {
//			Scanner sc = new Scanner(System.in);
//			
//			System.out.println("Saisir le libellé :");
//			String libelle = sc.nextLine();
//			
//			System.out.println("Saisir le prix :");
//			BigDecimal prix = sc.nextBigDecimal();
//			
//			
//			PreparedStatement myStatement = myConnection.prepareStatement(
//					"INSERT INTO produit (PRO_LIBELLE, PRO_PRIX, PRO_FOURNISSEUR_ID) VALUES (?, ?, ?)");
//			
//			myStatement.setString(1, libelle);
//			myStatement.setBigDecimal(2, prix);
//			myStatement.setFloat(3, 1);
//
//			myStatement.execute();
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Pb de connexion");
//		}
		
		
		
		IDAOProduit daoProduit = new DAOProduitSQL();
		IDAOFournisseur daoFournisseur = new DAOFournisseurSQL();
		
		try {
			Produit monNouveauProduit = new Produit();
			monNouveauProduit.setLibelle("Violon");
			monNouveauProduit.setPrix(new BigDecimal(100));
			monNouveauProduit.setFournisseur(new Fournisseur(0, "Yamaha"));
			
			daoProduit.save(monNouveauProduit);
		}
		
		catch (EmptyLibelleException e) {
			
		}
		
		
		List<Produit> mesProduits = daoProduit.findAll();

		// Parcours de la liste des produits
		for (Produit p : mesProduits) {
			System.out.println(p.getLibelle() + " " + p.getPrix());
		}
		System.out.println("------------------");

		
		// Parcours de la liste des fournisseurs
		for (Fournisseur f : daoFournisseur.findAll()) {
			System.out.println(f.getNom());
		}
		System.out.println("------------------");
		
		
		// Un fournisseur avec sa liste de produits
		Fournisseur monFournisseurAvecSesProduits = daoFournisseur.findByIdFetchingProduits(1);
		System.out.println(monFournisseurAvecSesProduits.getNom());
		System.out.println(monFournisseurAvecSesProduits.getProduits().size() + " produits :");
		
		for (Produit p : monFournisseurAvecSesProduits.getProduits()) {
			System.out.println(" - " + p.getLibelle() + " " + p.getPrix());
		}
		System.out.println("------------------");
		
		
		// Liste des fournisseurs qui contiennent ...
		for (Fournisseur f : daoFournisseur.findByNomContaining("ya")) {
			System.out.println(f.getNom());
		}
	}
}