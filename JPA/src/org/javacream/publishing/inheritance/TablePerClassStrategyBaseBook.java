package org.javacream.publishing.inheritance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Entity(name = "TablePerClassStrategyBaseBook")
//@Table(name = "BOOKS_TABLE", indexes={@Index(unique=false, columnList="price"), @Index(unique=false, columnList="title")})
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassStrategyBaseBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique=true, updatable=false, nullable=false)
	private String isbn;
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

	public TablePerClassStrategyBaseBook(String isbn, String title) {
		super();
		this.isbn = isbn;
		this.title = title;
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
		TablePerClassStrategyBaseBook other = (TablePerClassStrategyBaseBook) obj;
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

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	protected TablePerClassStrategyBaseBook() {
	}

	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;

}
