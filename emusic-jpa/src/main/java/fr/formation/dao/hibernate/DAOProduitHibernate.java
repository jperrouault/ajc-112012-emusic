package fr.formation.dao.hibernate;

import java.util.List;
import java.util.Optional;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;

public class DAOProduitHibernate extends DAOHibernate implements IDAOProduit {
	@Override
	public List<Produit> findAll() {
		return em
				.createQuery("select p from Produit p", Produit.class)
				.getResultList();
	}

	@Override
	public Produit findById(Integer id) {
		return em.find(Produit.class, id);
	}

	@Override
	public Produit save(Produit entity) {
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
	public void delete(Produit entity) {
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
		Produit leProduitASupprimer = new Produit();
		
		leProduitASupprimer.setId(id);
		delete(leProduitASupprimer);
	}

	
	
	
	@Override
	public Optional<Produit> findByLibelle(String libelle) {
		try {
//			TypedQuery<Produit> myQuery = em.createQuery("select p from Produit p where p.libelle = :libelle", Produit.class);
//			myQuery.setParameter("libelle", libelle);
//			Produit leProduit = myQuery.getSingleResult();
//			return leProduit;
			
//			Produit leProduit = em
//					.createQuery("select p from Produit p where p.libelle = :libelle", Produit.class)
//					.setParameter("libelle", libelle)
//					.getSingleResult();
//			return leProduit;
			
			
			return Optional.of(em
					.createQuery("select p from Produit p where p.libelle = :libelle", Produit.class)
					.setParameter("libelle", libelle)
					.getSingleResult());
		}
		
		catch (Exception ex) {
			return Optional.empty();
		}
	}

	@Override
	public List<Produit> findAllWithReclamation() {
		return em
				.createQuery("select p from Produit p"
						+ "		inner join p.achats a"
						+ "		inner join a.reclamation r", Produit.class)
//						+ "		where a.reclamation != null", Produit.class)
				.getResultList();
	}

}
