package org.javacream.publishing.basicmapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableIsbnWithSecondaryTable implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(table="BOOKS_ISBN")
	private int part1;
	@Column(table="BOOKS_ISBN")
	private int part2;
	@Column(table="BOOKS_ISBN")
	private int part3;
	@Column(table="BOOKS_ISBN")
	private int part4;

	public int getPart1() {
		return part1;
	}

	public int getPart2() {
		return part2;
	}

	public int getPart3() {
		return part3;
	}

	public int getPart4() {
		return part4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + part1;
		result = prime * result + part2;
		result = prime * result + part3;
		result = prime * result + part4;
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
		EmbeddableIsbnWithSecondaryTable other = (EmbeddableIsbnWithSecondaryTable) obj;
		if (part1 != other.part1)
			return false;
		if (part2 != other.part2)
			return false;
		if (part3 != other.part3)
			return false;
		if (part4 != other.part4)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Isbn [part1=" + part1 + ", part2=" + part2 + ", part3=" + part3
				+ ", part4=" + part4 + "]";
	}

	public EmbeddableIsbnWithSecondaryTable(int part1, int part2, int part3, int part4) {
		super();
		this.part1 = part1;
		this.part2 = part2;
		this.part3 = part3;
		this.part4 = part4;
	}
	
	
	@SuppressWarnings("unused")
	private EmbeddableIsbnWithSecondaryTable(){
		
	}
	
}
