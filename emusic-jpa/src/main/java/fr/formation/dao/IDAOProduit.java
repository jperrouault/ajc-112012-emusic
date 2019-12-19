package fr.formation.dao;

import fr.formation.model.Produit;

public interface IDAOProduit extends IDAO<Produit, Integer> {
	public Produit findByLibelle(String libelle);
}