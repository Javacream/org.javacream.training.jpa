package org.javacream.publishing.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("specialist")
public class SingleTableDiscriminatorValueSpecialistBook extends SingleTableDiscriminatorValueStrategyBaseBook {
	private static final long serialVersionUID = 1L;
	private String topic;
	protected SingleTableDiscriminatorValueSpecialistBook() {
		super();
	}
	public SingleTableDiscriminatorValueSpecialistBook(String isbn, String title, String topic) {
		super(isbn, title);
		this.topic = topic;
	}
	public String getTopic() {
		return topic;
	}
	
}
