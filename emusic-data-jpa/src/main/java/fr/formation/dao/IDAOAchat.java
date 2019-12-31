package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Achat;

public interface IDAOAchat extends JpaRepository<Achat, Integer> {
	
}