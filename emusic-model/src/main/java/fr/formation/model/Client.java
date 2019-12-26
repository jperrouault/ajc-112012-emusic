package fr.formation.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("2")
public class Client extends Personne {
	@Column(name = "CLI_DATE_NAISSANCE", nullable = false)
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateNaissance;

	@Column(name = "CLI_CHIFFRE_AFFAIRE", length = 10, precision = 2)
	private BigDecimal chiffreAffaire;
	
	
//	@ManyToMany
//	@JoinTable(
//		name = "achat",
//		joinColumns = @JoinColumn(name = "ACH_CLIENT_ID",
//						referencedColumnName = "PER_ID"),
//		
//		inverseJoinColumns = @JoinColumn(name = "ACH_PRODUIT_ID",
//						referencedColumnName = "PRO_ID")
//	)
//	private List<Produit> achatsDuClient;
	
	@OneToMany(mappedBy = "client")
	private List<Achat> achatsDuClient;
	
	
	@ManyToMany
	@JoinTable(
			name = "lieux",
			joinColumns = @JoinColumn(name = "LIEU_CLIENT_ID",
								referencedColumnName = "PER_ID"),
			inverseJoinColumns = @JoinColumn(name = "LIEU_ADRESSE_ID",
								referencedColumnName = "ADR_ID")
	)
	private List<Adresse> adresses;
	
	
	

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public BigDecimal getChiffreAffaire() {
		return chiffreAffaire;
	}

	public void setChiffreAffaire(BigDecimal chiffreAffaire) {
		this.chiffreAffaire = chiffreAffaire;
	}

	public List<Achat> getAchatsDuClient() {
		return achatsDuClient;
	}

	public void setAchatsDuClient(List<Achat> achatsDuClient) {
		this.achatsDuClient = achatsDuClient;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
}