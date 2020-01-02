package fr.formation.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOProduit;

@Service
public class ProduitService {
	@Autowired
	IDAOProduit daoProduit;
	
	@Transactional
	public void listeDesProduitsAvecClients() {
		daoProduit.findAll().forEach(p -> {
			System.out.println(p.getLibelle());
			
			p.getAchats().forEach(a -> {
				System.out.println(" - " + a.getClient().getPrenom());
			});
			
			System.out.println("---------------------");
		});
	}
}