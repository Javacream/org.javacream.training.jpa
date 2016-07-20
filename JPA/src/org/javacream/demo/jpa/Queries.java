package org.javacream.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class Queries {

	@Test public void testQueries(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Cat cat = new Cat("Thommy" + System.currentTimeMillis(), 3.99);
		entityManager.persist(cat);
		Cat cat2 = new Cat("Edgar" + System.currentTimeMillis(), 1.99);
		entityManager.persist(cat2);
		transaction.commit();
		entityManager.clear();
		transaction.begin();
		List<CatInfo> result = entityManager.createQuery("select new org.javacream.demo.jpa.CatInfo(cat.name, cat.weight) from Cat as cat").getResultList();
		System.out.println(result);
		List<Double> weightResult = entityManager.createQuery("select cat.weight from Cat as cat").getResultList();
		System.out.println(weightResult);
		entityManager.createQuery("update Cat set weight = 3").executeUpdate();
		transaction.commit();
		

	}
}
