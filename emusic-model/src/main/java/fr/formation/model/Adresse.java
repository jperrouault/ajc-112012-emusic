package fr.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "adresse")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADR_ID")
	private int id;

	@Column(name = "ADR_RUE", length = 300, nullable = false)
	@NotEmpty
	private String rue;

	@Column(name = "ADR_CODE_POSTAL", length = 5, nullable = false)
	@NotEmpty
	@Size(max = 5)
	private String codePostal;

	@Column(name = "ADR_VILLE", length = 30, nullable = false)
	@NotEmpty
	@Size(max = 30)
	private String ville;
	
	@OneToMany(mappedBy = "adresseLivraison")
	private List<Achat> achatsLivres;
	
	@ManyToMany(mappedBy = "adresses")
	private List<Client> clients;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Achat> getAchatsLivres() {
		return achatsLivres;
	}

	public void setAchatsLivres(List<Achat> achatsLivres) {
		this.achatsLivres = achatsLivres;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}