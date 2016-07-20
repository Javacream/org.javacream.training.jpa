package org.javacream.publishing;

import static org.javacream.publishing.Util.AUTHOR1;
import static org.javacream.publishing.Util.AUTHOR2;
import static org.javacream.publishing.Util.AUTHOR3;
import static org.javacream.publishing.Util.BOOK1;
import static org.javacream.publishing.Util.BOOK2;
import static org.javacream.publishing.Util.BOOKSTATISTIC1;
import static org.javacream.publishing.Util.PUBLISHER;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;
public class PublishingTests extends BaseJpaTest{

	@Test public void testPublisher(){
		
		entityManager.persist(BOOK1);
		entityManager.persist(BOOK2);
		entityManager.persist(AUTHOR1);
		entityManager.persist(AUTHOR2);
		entityManager.persist(AUTHOR3);
		AUTHOR1.addBook(BOOK1);
		AUTHOR2.addBook(BOOK1);
		AUTHOR3.addBook(BOOK1);
		AUTHOR3.addBook(BOOK2);
		AUTHOR1.addBook(BOOK2);
		BOOKSTATISTIC1.setBook(BOOK1);
		entityManager.persist(BOOKSTATISTIC1);
		Publisher publisher = PUBLISHER;
		publisher.addBooks(BOOK1);
		entityManager.persist(publisher);
		transaction.commit();
		entityManager.clear();
		transaction.begin();
		Publisher searchedPublisher = entityManager.find(Publisher.class, publisher.getName());
		Book book = entityManager.getReference(Book.class, 2l);
		searchedPublisher.addBooks(book);
		/* Bitte vermeiden!
		//Trigger lazy loading...
		searchedPublisher.getBooks().size();
		*/
		transaction.commit();
		System.out.println(searchedPublisher.getName() + " publishes " + searchedPublisher.getBooks().size() + " books");
		entityManager.clear();
		transaction.begin();
		Book searchedBook = entityManager.find(Book.class, 1l);
		transaction.commit();
		System.out.println("Book " + searchedBook.getTitle() + " is published by " + searchedBook.getPublisher().getName());
		entityManager.close();
	}


}
