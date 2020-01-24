package fr.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.dao.IDAOFournisseur;
import fr.formation.dao.IDAOProduit;
import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

@Controller
public class ProduitController {
	@Autowired
	private IDAOProduit daoProduit;
	
	@Autowired
	private IDAOFournisseur daoFournisseur;
	
	
	@GetMapping("/listeProduits")
	public String findAll(Model model) {
		model.addAttribute("produits", daoProduit.findAll());
		
//		return "/WEB-INF/views/listeProduits.jsp";
		return "listeProduits";
	}
	
	
	
	@GetMapping("/ajouterProduit")
	public String add(Model model) {
		model.addAttribute("produit", new Produit());
//		model.addAttribute("fournisseurs", daoFournisseur.findAll());
		return "formProduit";
	}
	
	
	@PostMapping("/ajouterProduit")
	public String add(@Valid @ModelAttribute Produit produit,
			BindingResult result, Model model
//						@RequestParam String libelle,
//						@RequestParam BigDecimal prix
		) {
		
		
//		Produit produit = new Produit();
//		try {
//			produit.setLibelle(libelle);
//			produit.setPrix(prix);
//		}
//		
//		catch (Exception e) {
//			
//		}
		//SI Y'A DES ERREURS, ON REAFFICHE LE FORMULAIRE
		if (result.hasErrors()) {
//			model.addAttribute("fournisseurs", daoFournisseur.findAll());
			return "formProduit";
		}
		
		
		daoProduit.save(produit);
		return "redirect:/listeProduits";
	}
	
	
	
	
	
	@GetMapping("/editerProduit")
	public String edit(Model model, @RequestParam int id) {
		Produit p = daoProduit.findById(id).orElse(null);

//		model.addAttribute("fournisseurs", daoFournisseur.findAll());
		model.addAttribute("produit", p);
		
		return "formProduit";
	}
	
	
	@PostMapping("/editerProduit")
	public String edit(@Valid @ModelAttribute Produit produit,
			BindingResult result, Model model) {
		
		//SI Y'A DES ERREURS, ON REAFFICHE LE FORMULAIRE
		if (result.hasErrors()) {
//			model.addAttribute("fournisseurs", daoFournisseur.findAll());
			return "formProduit";
		}
		
		daoProduit.save(produit);
		return "redirect:/listeProduits";
	}
	
	
	
	
	
	
	@GetMapping("/supprimerProduit")
	public String delete(@RequestParam int id) {
		daoProduit.deleteById(id);
		return "redirect:/listeProduits";
	}
	
	
	
	
	
	@ModelAttribute("fournisseurs")
	public List<Fournisseur> getFournisseurs() {
		return daoFournisseur.findAll();
	}
	
	
}