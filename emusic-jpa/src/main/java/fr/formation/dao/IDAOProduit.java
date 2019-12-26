package fr.formation.dao;

import java.util.List;
import java.util.Optional;

import fr.formation.model.Produit;

public interface IDAOProduit extends IDAO<Produit, Integer> {
	public Optional<Produit> findByLibelle(String libelle);
	public List<Produit> findAllWithReclamation();
}