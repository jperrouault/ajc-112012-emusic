package fr.formation.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "achat")
public class Achat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACH_ID")
	private int id;

	@Column(name = "ACH_DATE")
	private Date date;

	@Column(name = "ACH_PRIX")
	private BigDecimal prix;

	@ManyToOne
	@JoinColumn(name = "ACH_CLIENT_ID")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "ACH_PRODUIT_ID")
	private Produit produit;
	
	@OneToMany(mappedBy = "achat")
	private List<Commentaire> commentaires;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ACH_TRANSPORTEUR_ID")
	private Transporteur transporteur;
	
	@ManyToOne
	@JoinColumn(name = "ACH_ADRESSE_LIVRAISON_ID")
	private Adresse adresseLivraison;
	
	@OneToOne(mappedBy = "achat")
	private Reclamation reclamation;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Transporteur getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(Transporteur transporteur) {
		this.transporteur = transporteur;
	}

	public Adresse getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(Adresse adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}
}