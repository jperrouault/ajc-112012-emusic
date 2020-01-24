package fr.formation.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.Application;
import fr.formation.dao.IDAOProduit;


//@WebListener
public class SpringContextLoaderListener implements ServletContextListener {
	private AnnotationConfigApplicationContext ctx;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//CREATION DU CONTEXTE DE SPRING
		ctx = new AnnotationConfigApplicationContext(Application.class);
		
		//RECUPERATION DE LA DAO
		IDAOProduit daoProduit = ctx.getBean(IDAOProduit.class);
		
		//STOCKAGE DE LA DAO DANS LE SCOPE APPLICATION
		sce.getServletContext().setAttribute("daoProduit", daoProduit);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ctx.close();
	}
}