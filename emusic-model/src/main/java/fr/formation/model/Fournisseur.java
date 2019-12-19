package fr.formation.model;

import java.util.ArrayList;
import java.util.List;

public class Fournisseur {
	private int id;
	private String nom;
	private List<Produit> produits = new ArrayList<Produit>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Fournisseur() { }
	
	public Fournisseur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
}