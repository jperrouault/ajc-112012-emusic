package fr.formation.dao.hibernate;

import java.util.List;

import fr.formation.dao.IDAOAchat;
import fr.formation.model.Achat;

public class DAOAchatHibernate extends DAOHibernate implements IDAOAchat {
	@Override
	public List<Achat> findAll() {
		return em.createQuery("select a from Achat a", Achat.class)
				.getResultList();
	}

	@Override
	public Achat findById(Integer id) {
		return em.find(Achat.class, id);
	}

	@Override
	public Achat save(Achat entity) {
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
	public void delete(Achat entity) {
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
		Achat lAchatASupprimer = new Achat();
		
		lAchatASupprimer.setId(id);
		delete(lAchatASupprimer);
	}
}
