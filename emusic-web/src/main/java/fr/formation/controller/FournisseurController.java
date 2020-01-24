package fr.formation.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.model.Fournisseur;

@Controller
public class FournisseurController {
	@Autowired
	private IDAOFournisseur daoFournisseur;
	
	
	@GetMapping("/listeFournisseurs")
	public String findAll(Model model) {
		model.addAttribute("fournisseurs", daoFournisseur.findAll());
		return "listeFournisseurs";
	}
	
	
	@GetMapping("/listeProduitsFournisseur")
	@Transactional
	public String findProduitsByFournisseur(Model model, @RequestParam int id) {
//		Fournisseur fournisseur = daoFournisseur.findByIdFetchingProduits(id);
		Fournisseur fournisseur = daoFournisseur.findById(id).get();
		
		//DEMANDE A HIBERNATE DE CHARGER LA LISTE
		Hibernate.initialize(fournisseur.getProduits());
		
		model.addAttribute("produits", fournisseur.getProduits());
		return "listeProduits";
	}
	
	
	
	@GetMapping("/ajouterFournisseur")
	public String add(Model model) {
		model.addAttribute("fournisseur", new Fournisseur());
		return "formFournisseur";
	}
	
	@PostMapping("/ajouterFournisseur")
	public String add(@Valid @ModelAttribute Fournisseur fournisseur, BindingResult result) {
		if (result.hasErrors()) {
			return "formFournisseur";
		}
		
		daoFournisseur.save(fournisseur);
		return "redirect:/listeFournisseurs";
	}
	
	
	
	@GetMapping("/editerFournisseur")
	public String edit(@RequestParam int id, Model model) {
		model.addAttribute("fournisseur", daoFournisseur.findById(id).orElse(new Fournisseur()));
		return "formFournisseur";
	}
	
	@PostMapping("/editerFournisseur")
	public String edit(@Valid @ModelAttribute Fournisseur fournisseur, BindingResult result) {
		if (result.hasErrors()) {
			return "formFournisseur";
		}
		
		daoFournisseur.save(fournisseur);
		return "redirect:/listeFournisseurs";
	}
	
	
	
	@GetMapping("/supprimerFournisseur")
	public String delete(@RequestParam int id) {
		daoFournisseur.deleteById(id);
		return "redirect:/listeFournisseurs";
	}
}