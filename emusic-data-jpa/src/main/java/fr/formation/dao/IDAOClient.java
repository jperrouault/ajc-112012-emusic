package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Client;

public interface IDAOClient extends JpaRepository<Client, Integer> {
	
}