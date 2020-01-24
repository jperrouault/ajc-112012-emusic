package fr.formation.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.exception.EmptyLibelleException;

@Entity
@Table(name = "produit")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	private int id;

	@Column(name = "PRO_LIBELLE")
	@NotBlank(message = "Le libellé doit être saisi !")
	private String libelle;

	@Column(name = "PRO_PRIX")
	@Positive(message = "Le prix doit être supérieur à 0 !")
	private BigDecimal prix;

	@Column(name = "PRO_IMAGE_URL")
	private String imageUrl;

	@ManyToOne
	@JoinColumn(name = "PRO_FOURNISSEUR_ID")
	@JsonView(Views.Produit.class)
	private Fournisseur fournisseur;

//	@ManyToMany(mappedBy = "achatsDuClient")
//	private List<Client> clientsAcheteurs;

	@OneToMany(mappedBy = "produit")
	private List<Achat> achats;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) /*throws EmptyLibelleException*/ {
//		if (libelle == null || libelle.equals("")) {
//			throw new EmptyLibelleException();
//		}

		this.libelle = libelle;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	public Produit() { // POUR JPA ET/OU SPRING ... PAR EXEMPLE

	}

	public Produit(int id, String libelle, BigDecimal prix) throws EmptyLibelleException {
		this.id = id;
		// On passe par le setter pour gérer le cas "vide"
		this.setLibelle(libelle);
		this.prix = prix;
	}

	public Produit(int id, String libelle, BigDecimal prix, Fournisseur fournisseur) throws EmptyLibelleException {
		this(id, libelle, prix);
		this.fournisseur = fournisseur;
	}
}