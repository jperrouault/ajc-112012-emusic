package fr.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public interface IDAOProduit extends JpaRepository<Produit, Integer> {
	
	
	public List<Produit> findByLibelleContaining(String libelle);
	
	public Optional<Produit> findByLibelle(String libelle);
	
	@Query("select p from Produit p where p.libelle = :libelle")
	public Optional<Produit> findByLibelle2(@Param("libelle") String libelle);
	
	
	@Query("select p from Produit p where p.libelle = ?1")
	public Optional<Produit> findByLibelle3(String libelle);
	
	
	@Query("select p from Produit p inner join p.achats a inner join a.reclamation r")
	public List<Produit> findAllWithReclamation();

	public List<Produit> findByFournisseurId(int id);
		
	public List<Produit> findByFournisseur(Fournisseur fournisseur);
	
	public List<Produit> findByPrixBetween(Double a, Double b);
}