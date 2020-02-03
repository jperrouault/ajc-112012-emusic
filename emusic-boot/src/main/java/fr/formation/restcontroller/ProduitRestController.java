package fr.formation.restcontroller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOProduit;
import fr.formation.exception.EmptyLibelleException;
import fr.formation.model.Produit;
import fr.formation.projection.Views;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin("*")
public class ProduitRestController {
	@Autowired
	private IDAOProduit daoProduit;
	
	@GetMapping("/test")
	public Produit test() throws EmptyLibelleException {
		return new Produit(1, "Clarinette Test", new BigDecimal(4891));
	}
	
	
	@GetMapping
	@JsonView(Views.Produit.class)
	public List<Produit> findAll() {
		return this.daoProduit.findAll();
	}
	
	
	@PostMapping
	@JsonView(Views.Produit.class)
	public Produit save(@RequestBody Produit produit) {
		this.daoProduit.save(produit);
		
		for (SseEmitter emitter : this.emitters) {
			try {
				emitter.send("UN NOUVEAU PRODUIT");
			}
			
			catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		}
		
		return produit;
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		this.daoProduit.deleteById(id);
	}
	
	
	private List<SseEmitter> emitters = new ArrayList<SseEmitter>();
	
	@GetMapping("/sse")
	public SseEmitter sse() {
		SseEmitter emitter = new SseEmitter();
		
		//Actions à faire quand l'Event est complété
		emitter.onCompletion(() -> {
			//PERMET D'ETRE SUR QU'ON EST SEUL A UTILISER LA LISTE
			synchronized (this.emitters) {
				this.emitters.remove(emitter);
			}
		});
		
		
		//Actions à faire quand l'Event est en Timeout
		emitter.onTimeout(() -> {
			emitter.complete();
		});
		
		
		this.emitters.add(emitter);
		
		return emitter;
	}
	
}
