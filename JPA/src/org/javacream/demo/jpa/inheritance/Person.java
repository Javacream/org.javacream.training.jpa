package org.javacream.demo.jpa.inheritance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person implements Addressable{

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public Person(String name, String city, String street) {
		super();
		this.name = name;
		this.city = city;
		this.street = street;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", city=" + city
				+ ", street=" + street + "]";
	}

	protected Person(){
		
	}
	private String name;
	private String city;
	private String street;

	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public String getName() {
		return name;
	}
}
