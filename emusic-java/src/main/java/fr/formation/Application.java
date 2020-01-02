package fr.formation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import fr.formation.dao.IDAOAchat;
import fr.formation.dao.IDAOClient;
import fr.formation.dao.IDAOFournisseur;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Achat;
import fr.formation.model.Client;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;
import fr.formation.service.ProduitService;

@Configuration
@ComponentScan("fr.formation")
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(Application.class);
		
		myContext.getBean(Application.class).run(args);
		
		myContext.close();
	}

	@Autowired
	private IDAOProduit daoProduit;
	
	@Autowired
	private IDAOFournisseur daoFournisseur;
	
	@Autowired
	private IDAOClient daoClient;
	
	@Autowired
	private IDAOAchat daoAchat;
	
	@Autowired
	private ProduitService srvProduit;
	
	
	public void run(String[] args) {
		try {
//			Fournisseur monFournisseur = new Fournisseur();
//			Produit monProduit = new Produit();
//			
//			monFournisseur.setNom("TEST");
//			monFournisseur.setSociete("AMAZON");
//			
//			monProduit.setLibelle("Clarinette");
//			monProduit.setPrix(new BigDecimal(2500));
//			monProduit.setFournisseur(monFournisseur);
//			
//			daoFournisseur.save(monFournisseur);
//			daoProduit.save(monProduit);
//			
//			
//			
//			Client monClient = new Client();
//			
//			monClient.setNom("PERROUAULT");
//			monClient.setPrenom("Jérémy");
//			
//			daoClient.save(monClient);
//			
//			
//			Achat monAchat = new Achat();
//			
//			monAchat.setClient(monClient);
//			monAchat.setProduit(monProduit);
//			monAchat.setPrix(monProduit.getPrix());
//			
//			daoAchat.save(monAchat);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		//Liste des produits avec leur clients acheteurs
		srvProduit.listeDesProduitsAvecClients();
	}
	
	
	

	
	@Bean //ACTIVE LES @VALUE DANS LES FICHIERS DE CONFIGURATION JAVA
	public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}