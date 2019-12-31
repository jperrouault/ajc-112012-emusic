package fr.formation.dao.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.formation.dao.IDAOProduit;
import fr.formation.model.Produit;

@Repository
@Transactional
public class DAOProduitRepository implements IDAOProduit {
	@PersistenceContext
	private EntityManager em;
	
	
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
		if (entity.getId() == 0) { //AJOUT
			em.persist(entity);
		}
		
		else {//UPDATE
			entity = em.merge(entity);
		}
		
		return entity;
	}

	@Override
	public void delete(Produit entity) {
		em.remove(em.merge(entity));
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
				.getResultList();
	}
}