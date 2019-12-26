package fr.formation.dao.hibernate;

import java.util.List;

import fr.formation.dao.IDAOClient;
import fr.formation.model.Client;

public class DAOClientHibernate extends DAOHibernate implements IDAOClient {
	@Override
	public List<Client> findAll() {
		return em.createQuery("select c from Client c", Client.class)
				.getResultList();
	}

	@Override
	public Client findById(Integer id) {
		return em.find(Client.class, id);
	}

	@Override
	public Client save(Client entity) {
		em.getTransaction().begin(); //Démarrage TX
		
		try {
			if (entity.getId() == 0) { //AJOUT
				em.persist(entity);
			}
			
			else {//UPDATE
				entity = em.merge(entity);
			}
		
			em.getTransaction().commit(); //Commit TX - On applique les changements
		}
		
		catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); //Y'a un pb, on annule !
		}
		
		return entity;
	}

	@Override
	public void delete(Client entity) {
		try {
			em.getTransaction().begin(); //Démarrage TX
			em.remove(em.merge(entity));
			em.getTransaction().commit(); //Commit TX
		}
		
		catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); //Y'a un pb, on annule !
		}
	}

	@Override
	public void deleteById(Integer id) {
		Client leClientASupprimer = new Client();
		
		leClientASupprimer.setId(id);
		delete(leClientASupprimer);
	}
}
