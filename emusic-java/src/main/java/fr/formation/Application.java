package fr.formation;



import java.util.Date;

import fr.formation.dao.IDAOAchat;
import fr.formation.dao.IDAOClient;
import fr.formation.dao.IDAOProduit;
import fr.formation.dao.hibernate.DAOAchatHibernate;
import fr.formation.dao.hibernate.DAOClientHibernate;
import fr.formation.dao.hibernate.DAOHibernate;
import fr.formation.dao.hibernate.DAOProduitHibernate;
import fr.formation.exception.EmptyLibelleException;
import fr.formation.model.Achat;
import fr.formation.model.Produit;
import fr.formation.model.Transporteur;

public class Application {
	public static void main(String[] args) throws EmptyLibelleException {
		IDAOProduit daoProduit = new DAOProduitHibernate();
		IDAOClient daoClient = new DAOClientHibernate();
		IDAOAchat daoAchat = new DAOAchatHibernate();
		
		
//		daoProduit.findAll().forEach(p -> {
//			System.out.println(p.getLibelle());
//		});
		

//		new Date(); //Date du jour de maintenant
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		formatter.parse("02/11/1987");
		

//		Client monClient = new Client();
//		monClient.setNom("PERROUAULT");
//		monClient.setPrenom("Jérémy");
//		monClient.setDateNaissance(Date.valueOf("1987-11-02"));
		
//		daoClient.save(monClient);
		
		
//		Produit monProduit = new Produit(0, "Clarinette", new BigDecimal(5000));
//		daoProduit.save(monProduit);
		
//		daoClient.findAll().forEach(c -> {
//			System.out.println(c.getPrenom());
//		});
		
		
//		Optional<Produit> leProduitOp = daoProduit.findByLibelle("GJJ");
//		if (leProduitOp.isPresent()) {
//			System.out.println(leProduitOp.get().getId());
//		}
		
		
//		System.out.println(
//				daoProduit.findByLibelle("GJJ")
//				//Retourne le produit s'il est présent, sinon un nouveau produit
//					.orElse(new Produit())
//				.getId()
//		);
//		
//		
//		daoProduit.findAllWithReclamation().forEach(p -> {
//			System.out.println(p.getLibelle());
//		});
		
		
		
		Produit monProduit = daoProduit.findById(1);
		Achat monAchat = new Achat();
		Transporteur monTransporteur = new Transporteur();
		
		monTransporteur.setNom("LA POSTE");
		
		monAchat.setProduit(monProduit);
		monAchat.setDate(new Date());
		monAchat.setPrix(monProduit.getPrix());
		monAchat.setTransporteur(monTransporteur);
		
		daoAchat.save(monAchat);
		
		
		
		
		DAOHibernate.close();
		System.out.println("! FIN !");
		
		
		
		
		
//		Fournisseur f = null;
//		for (Produit p : f.getProduits()) {
//			for (Achat a : p.getAchats()) {
//				System.out.println(a.getClient().getPrenom());
//			}
//		}
		
		
		
		
		
		
		
		
		
		
		
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
		
		
		
//		IDAOProduit daoProduit = new DAOProduitSQL();
//		IDAOFournisseur daoFournisseur = new DAOFournisseurSQL();
		
//		try {
//			Produit monNouveauProduit = new Produit();
//			monNouveauProduit.setLibelle("Violon");
//			monNouveauProduit.setPrix(new BigDecimal(100));
//			monNouveauProduit.setFournisseur(new Fournisseur(0, "Yamaha"));
//			
//			daoProduit.save(monNouveauProduit);
//		}
//		
//		catch (EmptyLibelleException e) {
//			
//		}
		
		
//		List<Produit> mesProduits = daoProduit.findAll();
//
//		// Parcours de la liste des produits
//		daoProduit.findAll().forEach(p -> { System.out.println(p.getLibelle() + " " + p.getPrix()); });
//		System.out.println("------------------");
//
//		
//		// Parcours de la liste des fournisseurs
//		for (Fournisseur f : daoFournisseur.findAll()) {
//			System.out.println(f.getNom());
//		}
//		System.out.println("------------------");
//		
//		
//		// Un fournisseur avec sa liste de produits
//		Fournisseur monFournisseurAvecSesProduits = daoFournisseur.findByIdFetchingProduits(1);
//		System.out.println(monFournisseurAvecSesProduits.getNom());
//		System.out.println(monFournisseurAvecSesProduits.getProduits().size() + " produits :");
//		
//		for (Produit p : monFournisseurAvecSesProduits.getProduits()) {
//			System.out.println(" - " + p.getLibelle() + " " + p.getPrix());
//		}
//		System.out.println("------------------");
//		
//		
//		// Liste des fournisseurs qui contiennent ...
//		for (Fournisseur f : daoFournisseur.findByNomContaining("ya")) {
//			System.out.println(f.getNom());
//		}
	}
}