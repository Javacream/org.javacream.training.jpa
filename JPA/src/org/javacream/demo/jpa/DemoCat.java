package org.javacream.demo.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DEMOCATS")
public class DemoCat {
	
	private String name;
	private double weight;

	@Basic
	@Column(name="CAT_COLOR", nullable=false, updatable=false)
	private String furColor;
	
	private StringBuilder meawGenerator;
	@Transient	private Thread heartBeat;
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public String getFurColor() {
		return furColor;
	}
	public DemoCat(String name, double weight, String furColor) {
		super();
		this.name = name;
		this.weight = weight;
		this.furColor = furColor;
	}
	
	public void annoy(){
		

	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((furColor == null) ? 0 : furColor.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		DemoCat other = (DemoCat) obj;
		if (furColor == null) {
			if (other.furColor != null)
				return false;
		} else if (!furColor.equals(other.furColor))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	
	// +++++++++++++++ START JPA ++++++++++++++++++++++++
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) //@GeneratedValue: Beim entityManager.persist MUSS jetzt der Datenbank-Insert erfolgen!
	private Long id;

	//private wird von Hibernate unterstützt
	@SuppressWarnings("unused")
	private DemoCat(){
		
	}
	
	{
		id = System.currentTimeMillis() + this.hashCode();
	}
	@Override
	public String toString() {
		return "DemoCat [name=" + name + ", weight=" + weight + ", furColor="
				+ furColor + ", id=" + id + "]";
	}
}
