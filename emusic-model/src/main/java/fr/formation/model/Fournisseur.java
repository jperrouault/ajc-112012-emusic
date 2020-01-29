package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;


@Entity
@DiscriminatorValue("1")
//@Table(name="fournisseur")
//@PrimaryKeyJoinColumn(name = "FOU_ID", referencedColumnName = "PER_ID")
public class Fournisseur extends Personne {
	@Column(name="FOU_SOCIETE")
	@NotEmpty(message = "Le nom de la société doit être saisi !")
	@JsonView(Views.Fournisseur.class)
	private String societe;
	
	@OneToMany(mappedBy = "fournisseur")
	@JsonView(Views.FournisseurFetchingProduits.class)
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