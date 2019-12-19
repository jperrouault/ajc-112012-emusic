package fr.formation.model;

import java.math.BigDecimal;

import fr.formation.exception.EmptyLibelleException;

public class Produit {
	private int id;
	private String libelle;
	private BigDecimal prix;
	private Fournisseur fournisseur;
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) throws EmptyLibelleException {
		if (libelle == null || libelle.equals("")) {
			throw new EmptyLibelleException();
		}
		
		this.libelle = libelle;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	
	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Produit() { //POUR JPA ET/OU SPRING ... PAR EXEMPLE
		
	}
	
	public Produit(int id, String libelle, BigDecimal prix) throws EmptyLibelleException {
		this.id = id;
		//On passe par le setter pour gérer le cas "vide"
		this.setLibelle(libelle);
		this.prix = prix;
	}
	
	public Produit(int id, String libelle, BigDecimal prix, Fournisseur fournisseur) throws EmptyLibelleException {
		this(id, libelle, prix);
		this.fournisseur = fournisseur;
	}
}