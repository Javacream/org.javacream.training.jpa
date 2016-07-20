package org.javacream.publishing.basicmapping;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class BookTests extends BaseJpaTest{

	@Test public void testBooks(){
		Book book = new Book("ISBN1", "Title1");
		entityManager.persist(book);
		book.setPrice(19.99);
		book.setPages(200);
		Book book2 = new Book("ISBN3", "Title1");
		entityManager.persist(book2);
		transaction.commit();
	}
	@Test public void testBooksWithIsbn(){
		BookWithIsbn book = new BookWithIsbn(new Isbn(1, 2, 3, 4), "Title1");
		entityManager.persist(book);
		book.setPrice(19.99);
		book.setPages(200);
		transaction.commit();
	}
	@Test public void testBooksWithTransientIsbn(){
		BookWithTransientIsbn book = new BookWithTransientIsbn(new Isbn(1, 2, 3, 4), "Title1");
		entityManager.persist(book);
		book.setPrice(19.99);
		book.setPages(200);
		transaction.commit();
	}
	@Test public void testBooksWithIsbnAndCallback(){
		BookWithIsbnAndCallbacks book = new BookWithIsbnAndCallbacks(new Isbn(1, 2, 3, 4), "Title1");
		entityManager.persist(book);
		book.setPrice(19.99);
		book.setPages(200);
		transaction.commit();
	}

	@Test public void testBooksWithEmbeddableIsbn(){
		BookWithEmbeddableIsbn book = new BookWithEmbeddableIsbn(new EmbeddableIsbn(1, 2, 3, 4), "Title1");
		entityManager.persist(book);
		book.setPrice(19.99);
		book.setPages(200);
		transaction.commit();
	}
	@Test public void testBooksWithEmbeddableIsbnAndSecondaryTable(){
		BookWithEmbeddableIsbnAndSecondaryTable book = new BookWithEmbeddableIsbnAndSecondaryTable(new EmbeddableIsbnWithSecondaryTable(1, 2, 3, 4), "Title1");
		entityManager.persist(book);
		book.setPrice(19.99);
		book.setPages(200);
		transaction.commit();
	}

	
	
}
