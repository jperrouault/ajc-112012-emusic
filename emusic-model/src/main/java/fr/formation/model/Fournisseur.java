package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;


@Entity
@DiscriminatorValue("1")
//@Table(name="fournisseur")
//@PrimaryKeyJoinColumn(name = "FOU_ID", referencedColumnName = "PER_ID")
public class Fournisseur extends Personne {
	@Column(name="FOU_SOCIETE")
	@NotEmpty
	private String societe;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<Produit> produits;

	
	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Fournisseur() {
	}

	public Fournisseur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
}