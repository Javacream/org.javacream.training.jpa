package org.javacream.util.test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;

public class BaseJpaTest {

	protected EntityManager entityManager;
	protected static EntityManagerFactory entityManagerFactory;
	protected EntityTransaction transaction;
	@BeforeClass public static void setUpEntityManager(){
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA_MySQL");
		
	}
	
	@Before public void setUp(){
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	
}
