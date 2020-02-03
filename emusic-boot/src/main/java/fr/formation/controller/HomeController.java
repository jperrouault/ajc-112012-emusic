package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.model.Produit;

@Controller
public class HomeController {

//	@RequestMapping("/home")
//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	@GetMapping("/home")
	
//	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	@GetMapping("/home")
//	@PostMapping("/home")
	public String home() {
//		return "/WEB-INF/views/home.jsp";
		return "home";
	}
	
	
	
	
	@GetMapping("/produit/{idProduit}")
	public String findProduit(@PathVariable int idProduit) {
		return "ficheProduit";
	}
	
	
	
	
	@PostMapping("/home")
	public String newsletter(
			
//			@RequestParam String mail,
//			@RequestParam("age") int unAge
			
//			@RequestParam String libelle,
//			@RequestParam BigDecimal prix
			
			@ModelAttribute Produit produit
			
			) {
		
//		String mail = req.getParameter("mail");
//		int age = Integer.parseInt( req.getParameter("age") );
		
		return "unepage";
	}
	
	
	
	
	
	

}