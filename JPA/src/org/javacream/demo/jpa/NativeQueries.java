package org.javacream.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

public class NativeQueries {

	@Test public void testNativeQueries(){
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
		Query query = entityManager.createNativeQuery("select catId, name, weight from cats", Cat.class);
		List<Cat> cats = query.getResultList();
		System.out.println(cats);
		cats.get(0).setWeight(19.99);
		transaction.commit();
		transaction.begin();
		entityManager.createNativeQuery("update cats set weight=5.00").executeUpdate();
		transaction.commit();
		

	}
}
