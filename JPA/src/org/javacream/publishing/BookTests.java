package org.javacream.publishing;

import java.util.Date;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class BookTests extends BaseJpaTest{

	@Test public void testBooksWithIsbn(){
		Book book = new Book(new Isbn(1, 2, 3, 4), "Title1");
		book.setMedium(BookMedium.PRINT);
		book.addTags("sport", "politics");
		book.addRevision(new Revision("Sawitzki", new Date(), "1.0"));
		book.addRevision(new Revision("Sawitzki", new Date(), "1.1"));
		Book book2 = new Book(new Isbn(1, 2, 3, 5), "Title2");
		book2.setMedium(BookMedium.AUDIO);
		book2.addRevision(new Revision("Meier", new Date(), "0.5"));
		book2.addRevision(new Revision("Metzger", new Date(), "0.6"));
		book2.addTags("politics");
		entityManager.persist(book);
		entityManager.persist(book2);
		book.setPrice(19.99);
		book.setPages(200);
		transaction.commit();
		transaction.begin();
		entityManager.remove(book);
		book2.addRevision(new Revision("Schmidt", new Date(), "1.0"));
		transaction.commit();
	}
	
}
