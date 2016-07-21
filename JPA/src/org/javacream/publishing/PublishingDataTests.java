package org.javacream.publishing;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class PublishingDataTests extends BaseJpaTest {

	@Test
	public void testPublisher() {
		Util.createPublishers(entityManager);
		transaction.commit();
		entityManager.clear();
		EntityManager entityManager2 = BaseJpaTest.entityManagerFactory.createEntityManager();
		EntityTransaction transaction2 = entityManager2.getTransaction();
		transaction.begin();
		transaction2.begin();
		Author author = entityManager.find(Author.class, 1l);
		Author author2 = entityManager2.find(Author.class, 1l);
		
		author.setLastname("Hugo");
		author2.setLastname("Emil");
		System.out.println(author);
//		//Zwischenzeitliche Änderungen in der Datenbank werden hier eingespielt
//		entityManager.refresh(author);
//		//Andere Richtung
//		entityManager.flush();
//		transaction.begin();
//		entityManager.lock(author, LockModeType.PESSIMISTIC_WRITE);
		transaction.commit();
		transaction2.commit();
		entityManager.close();
		
	}

}
