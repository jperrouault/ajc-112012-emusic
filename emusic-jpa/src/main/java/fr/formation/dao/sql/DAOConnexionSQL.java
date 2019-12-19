package fr.formation.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DAOConnexionSQL {
	protected static Connection connection = null;
	protected static EntityManager em = null;
	
	public DAOConnexionSQL() {
		this.setup();
	}
	
	
	public void setup() {
		try {
			if (em == null ) {
				em = new EntityManager();
			}
			
			if (connection == null ) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emusic?serverTimezone=UTC", "root", "");
			}
		}

		catch (Exception e) {
			System.out.println("Pb de connexion");
		}
	}
}