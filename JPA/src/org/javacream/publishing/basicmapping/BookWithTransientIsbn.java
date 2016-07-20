package org.javacream.publishing.basicmapping;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "BooksWithTransientIsbn")
@Table(name = "BOOKS_TABLE_WITH_TRANSIENT_ISBN", indexes={@Index(unique=false, columnList="price"), @Index(unique=false, columnList="title")})
public class BookWithTransientIsbn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique=true, updatable=false, nullable=false)
	private String isbnAsString;
	
	public void prePersist(){
		isbnAsString = isbn.toString();
	}
	
	public void postLoad(){
		StringTokenizer stringTokenizer = new StringTokenizer(isbnAsString, "-");
		int part1 = Integer.parseInt(stringTokenizer.nextToken());
		int part2 = Integer.parseInt(stringTokenizer.nextToken());
		int part3 = Integer.parseInt(stringTokenizer.nextToken());
		int part4 = Integer.parseInt(stringTokenizer.nextToken());
		isbn = new Isbn(part1, part2, part3, part4);
	}
	
	@Transient private Isbn isbn;
	@Column(unique=false, updatable=false, nullable=false, length=20)
	private String title;
	
	
	private int pages;
	
	@Column(precision=2, scale=2)
	private double price;
	
	@Transient
	private boolean available;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public BookWithTransientIsbn(Isbn isbn, String title) {
		super();
		this.isbn = isbn;
		this.title = title;
		prePersist();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookWithTransientIsbn other = (BookWithTransientIsbn) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Isbn getIsbn() {
		if (isbn == null){
			postLoad();
		}
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	@Id
	private Long id;

	{
		id = System.currentTimeMillis() + this.hashCode();
	}
}
