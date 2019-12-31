package fr.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.formation.dao.IDAOProduit;

@Configuration
@ComponentScan("fr.formation")
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(Application.class);
		
		myContext.getBean(Application.class).run(args);
		
		myContext.close();
	}
	
	@Autowired
	private IDAOProduit daoProduit;
	
	public void run(String[] args) {
		daoProduit.findAll().forEach(p -> {
			System.out.println(p.getLibelle());
		});
	}
}