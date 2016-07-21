package org.javacream.publishing;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class PublishingOptimisticLockingTests extends BaseJpaTest {

	@Test
	public void testPublisher() {
		entityManager.persist(Util.AUTHOR1);

		transaction.commit();
		entityManager.clear();
		EntityManager entityManager2 = BaseJpaTest.entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction2 = entityManager2.getTransaction();

		transaction.begin();
		transaction2.begin();

		Author author = entityManager.find(Author.class, 1l);
		Author author2 = entityManager2.find(Author.class, 1l);

		author.setLastname("Hugo");
		author2.setLastname("Emil");
//		System.out.println(author);
		transaction.commit();
		try {
			transaction2.commit();
		} catch (RollbackException rollbackException) {
			entityManager2.clear();
			System.out.println("Detected conflict: " + rollbackException);
			Author author3 = entityManager2.find(Author.class, 1l);
			System.out.println("Author is " + author3);
			author3.setLastname("Emil");
			transaction2.begin();
			transaction2.commit();
		}
		entityManager.close();

	}

}
