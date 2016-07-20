package org.javacream.demo.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class CatJpaTest {

	
	@Test public void testCat(){
		//DROP CREATE in persistence.xml
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cat cat = new Cat("Thommy" + System.currentTimeMillis(), 3.99);//State= transient
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(cat); //State=attached
		transaction.commit();
		//cat ist immer noch attached!
		entityManager.close(); //State = detached

		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		
		cat.setWeight(9.99);
		transaction.begin();
		Cat searchedCat = entityManager.createQuery("select cat from Cat as cat where cat.catId=1", Cat.class).getSingleResult();
		//searchedCat ist attached
		Cat mergedCat = entityManager.merge(cat);//cat ist immer noch detached!!!!
		cat.setWeight(1.99);
		System.out.println("identity:" + (searchedCat == cat));
		System.out.println("identity:" + (searchedCat == mergedCat));
		transaction.commit();
		
		
		
	}
	
	
	
}
