package org.javacream.publishing.inheritance;

import javax.persistence.Entity;

@Entity
public class TablePerClassSpecialistBook extends TablePerClassStrategyBaseBook {
	private static final long serialVersionUID = 1L;
	private String topic;
	protected TablePerClassSpecialistBook() {
		super();
	}
	public TablePerClassSpecialistBook(String isbn, String title, String topic) {
		super(isbn, title);
		this.topic = topic;
	}
	public String getTopic() {
		return topic;
	}
	
}
