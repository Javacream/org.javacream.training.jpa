package org.javacream.publishing.inheritance;

import java.util.List;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class BooksInheritanceTests extends BaseJpaTest {

	@Test public void testJoinedStrategy(){
		JoinedSchoolBook schoolBook = new JoinedSchoolBook("ISBN1", "Title1", "history", 7);
		entityManager.persist(schoolBook);
		JoinedSpecialistBook specialistBook = new JoinedSpecialistBook("ISBN2", "Title2", "physics");
		entityManager.persist(specialistBook);
		transaction.commit();
		List<JoinedStrategyBaseBook> books = entityManager.createQuery("select book from JoinedStrategyBaseBook as book", JoinedStrategyBaseBook.class).getResultList();
		System.out.println(books);
	}
	@Test public void testTablePerClassStrategy(){
		TablePerClassSchoolBook schoolBook = new TablePerClassSchoolBook("ISBN1", "Title1", "history", 7);
		entityManager.persist(schoolBook);
		TablePerClassSpecialistBook specialistBook = new TablePerClassSpecialistBook("ISBN2", "Title2", "physics");
		entityManager.persist(specialistBook);
		transaction.commit();
		List<TablePerClassStrategyBaseBook> books = entityManager.createQuery("select book from TablePerClassStrategyBaseBook as book", TablePerClassStrategyBaseBook.class).getResultList();
		System.out.println(books);
	}
	@Test public void testSingleTableDiscriminatorValueStrategy(){
		SingleTableDiscriminatorValueSchoolBook schoolBook = new SingleTableDiscriminatorValueSchoolBook("ISBN1", "Title1", "history", 7);
		entityManager.persist(schoolBook);
		SingleTableDiscriminatorValueSpecialistBook specialistBook = new SingleTableDiscriminatorValueSpecialistBook("ISBN2", "Title2", "physics");
		entityManager.persist(specialistBook);
		transaction.commit();
		List<SingleTableDiscriminatorValueStrategyBaseBook> books = entityManager.createQuery("select book from SingleTableDiscriminatorValueStrategyBaseBook as book", SingleTableDiscriminatorValueStrategyBaseBook.class).getResultList();
		System.out.println(books);
	}

	@Test public void testSingleTableDiscriminatorFormulaStrategy(){
		SingleTableDiscriminatorFormulaSchoolBook schoolBook = new SingleTableDiscriminatorFormulaSchoolBook("ISBN1", "Title1", "history", 7);
		entityManager.persist(schoolBook);
		SingleTableDiscriminatorFormulaSpecialistBook specialistBook = new SingleTableDiscriminatorFormulaSpecialistBook("ISBN2", "Title2", "physics");
		entityManager.persist(specialistBook);
		transaction.commit();
		List<SingleTableDiscriminatorFormulaStrategyBaseBook> books = entityManager.createQuery("select book from SingleTableDiscriminatorFormulaStrategyBaseBook as book", SingleTableDiscriminatorFormulaStrategyBaseBook.class).getResultList();
		System.out.println(books);
	}

}
