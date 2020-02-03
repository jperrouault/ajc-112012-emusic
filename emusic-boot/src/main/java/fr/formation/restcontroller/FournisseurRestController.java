package fr.formation.restcontroller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.model.Fournisseur;
import fr.formation.projection.Views;

@RestController
@RequestMapping("/api/fournisseur")
@CrossOrigin("*")
public class FournisseurRestController {
	@Autowired
	private IDAOFournisseur daoFournisseur;
	
	
	@GetMapping
	@JsonView(Views.Fournisseur.class)
	public List<Fournisseur> findAll() {
		return this.daoFournisseur.findAll();
	}
	
	
	@GetMapping("/{id}")
	@JsonView(Views.FournisseurFetchingProduits.class)
	@Transactional
	public Fournisseur findById(@PathVariable int id) {
//		return this.daoFournisseur.findByIdFetchingProduits(id);
		
		Fournisseur f = daoFournisseur.findById(id).get();
		
		Hibernate.initialize(f.getProduits());
		
		return f;
	}
}
