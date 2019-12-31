package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Fournisseur;

public interface IDAOFournisseur extends JpaRepository<Fournisseur, Integer> {
	@Query("select f from Fournisseur f left join f.produits p")
	public Fournisseur findByIdFetchingProduits(Integer id);
	
	//Récupérer les fournisseurs dont le nom contient qqch
	public List<Fournisseur> findByNomContaining(String nom);
}