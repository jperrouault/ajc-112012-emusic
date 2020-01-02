package fr.formation.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.formation.Application;
import fr.formation.model.Produit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class DAOProduitTest {
	@Autowired(required = false)
	private IDAOProduit daoProduit;
	
	
	@Test
	public void testDaoProduitExists() {
		assertNotNull(daoProduit);
	}
	
	
	@Test
	public void testFindById() {
		try {
			Optional<Produit> optionalProduit = daoProduit.findById(1);
			assertNotNull(optionalProduit);
			assertTrue(optionalProduit.isPresent());
			assertEquals(1, optionalProduit.get().getId());
		}
		
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testSaveAdd() {
		try {
			Produit monProduit = new Produit();
			
			monProduit.setLibelle("Un produit de test");
			monProduit.setPrix(new BigDecimal(10));
			
			assertEquals(0, monProduit.getId());
			
			daoProduit.save(monProduit);
			
			assertNotEquals(0, monProduit.getId());
			
			assertTrue(daoProduit.findById(monProduit.getId()).isPresent());
		}
		
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testDelete() {
		try {
			daoProduit.deleteById(1);
			Optional<Produit> optionalProduit = daoProduit.findById(1);
			assertNotNull(optionalProduit);
			assertFalse(optionalProduit.isPresent());
		}
		
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
}