package fr.formation.dao;

import java.util.List;

import fr.formation.model.Fournisseur;

public interface IDAOFournisseur extends IDAO<Fournisseur, Integer> {
	public Fournisseur findByIdFetchingProduits(Integer id);
	
	//Récupérer les fournisseurs dont le nom contient qqch
	public List<Fournisseur> findByNomContaining(String nom);
}