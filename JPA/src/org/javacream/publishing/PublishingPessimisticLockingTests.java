package org.javacream.publishing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class PublishingPessimisticLockingTests extends BaseJpaTest {

	@Test
	public void testPublisher() {

		entityManager.persist(Util.BOOK1);

		transaction.commit();
		entityManager.clear();
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		final EntityManager entityManager2 = BaseJpaTest.entityManagerFactory
				.createEntityManager();
		final EntityTransaction transaction2 = entityManager2.getTransaction();

		transaction.begin();
		transaction2.begin();

		executorService.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println("entityManager1 tries to acquire lock...");
				Book book = entityManager.find(Book.class, 1l,
						LockModeType.PESSIMISTIC_WRITE);
				// entityManager.lock(book, LockModeType.PESSIMISTIC_WRITE);
				System.out
						.println("entityManager1 tries to acquire lock success!");
				book.setPrice(39.55);
				System.out.println("entityManager1 committing...");
				transaction.commit();
				System.out.println("entityManager1 committing done");
			}

		});
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println("entityManager2 tries to acquire lock...");
				Book book2 = entityManager2.find(Book.class, 1l,
						LockModeType.PESSIMISTIC_WRITE);
				// entityManager2.lock(book2, LockModeType.PESSIMISTIC_WRITE);
				System.out
						.println("entityManager2 tries to acquire lock success!");
				book2.setPages(201);
				System.out.println("entityManager2 committing...");
				transaction2.commit();
				System.out.println("entityManager2 committing done");
			}

		});

		Object sync = new Object();
		synchronized (sync) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
