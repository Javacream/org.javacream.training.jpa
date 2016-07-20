package org.javacream.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class JpaNativeTest {

	
	@Test public void testTransactionScript(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createNativeQuery("create table MESSAgES (description varchar(1024), info varchar(1024))").executeUpdate();
		entityManager.createNativeQuery("insert into MESSAGES values('Hugo', 'Egal')").executeUpdate();
		List<Object[]> result = entityManager.createNativeQuery("select * from MESSAGES").getResultList();
		System.out.println(result.get(0)[1]);
		List<Object[]> result2 = entityManager.createNativeQuery("select * from MESSAGES").getResultList();
		
		System.out.println("identity? " + (result == result2));
		transaction.commit();
		entityManager.close();
	
	}
}
