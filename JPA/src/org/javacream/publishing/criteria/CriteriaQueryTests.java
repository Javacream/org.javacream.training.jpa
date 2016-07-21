package org.javacream.publishing.criteria;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.javacream.publishing.Author;
import org.javacream.publishing.Author_;
import org.javacream.publishing.Book;
import org.javacream.publishing.Book_;
import org.javacream.publishing.Util;
import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class CriteriaQueryTests extends BaseJpaTest{

	//@Test 
	public void testSimpleCriterias(){
		Util.createPublishers(entityManager);
		transaction.commit();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> bookClause = criteriaQuery.from(Book.class);
		//criteriaQuery.where(criteriaBuilder.equal(bookClause.get("title"), criteriaBuilder.parameter(String.class, "titleParam")));
		criteriaQuery.where(criteriaBuilder.equal(bookClause.get(Book_.title), criteriaBuilder.parameter(String.class, "titleParam")));
		TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
		query.setParameter("titleParam", "Title12");
		List<Book> result = query.getResultList();
		System.out.println(result);
	}
	
	@Test public void findBooksForAuthor(){
		Util.createPublishers(entityManager);
		transaction.commit();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> bookRoot = criteriaQuery.from(Book.class);
		SetJoin<Book, Author> join = bookRoot.join(Book_.authors);
//		ListJoin<Book, Revision> revisionSet = bookRoot.join(Book_.revisions);
		criteriaQuery.where(criteriaBuilder.equal(join.get(Author_.lastname), "AuthorLastname3"));
		criteriaQuery.orderBy(criteriaBuilder.desc(bookRoot.get(Book_.price)));
		TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
		System.out.println(query.getResultList());
	
		//ODER
		System.out.println(entityManager.createNativeQuery("select book0_.id as id1_2_, book0_.part1 as part2_2_, book0_.part2 as part3_2_, book0_.part3 as part4_2_, book0_.part4 as part5_2_, book0_.medium as medium6_2_, book0_.pages as pages7_2_, book0_.price as price8_2_, book0_.publisher_name as publish10_2_, book0_.title as title9_2_ from BOOKS book0_ inner join BOOKS_AUTHORS authors1_ on book0_.id=authors1_.ISBN inner join AUTHORS author2_ on authors1_.AUTHOR_ID=author2_.authorId where author2_.lastname=? order by book0_.price desc").getResultList());
	}
}
