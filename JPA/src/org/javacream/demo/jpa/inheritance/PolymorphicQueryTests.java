package org.javacream.demo.jpa.inheritance;

import java.util.List;

import javax.persistence.Query;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class PolymorphicQueryTests extends BaseJpaTest{

	@Test public void testPolymorphicQuery(){
		Person person1 = new Person("Sawitzki", "München", "Marienplatz");
		Person person2 = new Person("Mustermann", "Berlin", "Alexanderplatz");
		Company company = new Company("Integrata", "Stuttgart", "Zettachring");
		entityManager.persist(person1);
		entityManager.persist(person2);
		entityManager.persist(company);
		transaction.commit();
		Query query = entityManager.createQuery("select a from org.javacream.demo.jpa.inheritance.Addressable as a");
		@SuppressWarnings("unchecked")
		List<Addressable> addressables = query.getResultList();
		System.out.println(addressables);
	}
}
