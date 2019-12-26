package fr.formation.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOHibernate {
	protected static EntityManagerFactory emf = null;
	protected EntityManager em = null;
	
	
	public DAOHibernate() {
		if (emf == null) { //Création de EMF si non existant
			emf = Persistence.createEntityManagerFactory("EMusicUnit");
		}
		
		if (emf != null) { //Création de EM pour chaque instance
			this.em = emf.createEntityManager();
		}
	}
	
	public static void close() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}
}