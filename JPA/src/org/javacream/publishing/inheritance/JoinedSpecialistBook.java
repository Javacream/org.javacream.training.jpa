package org.javacream.publishing.inheritance;

import javax.persistence.Entity;

@Entity
public class JoinedSpecialistBook extends JoinedStrategyBaseBook {
	private static final long serialVersionUID = 1L;
	private String topic;
	protected JoinedSpecialistBook() {
		super();
	}
	public JoinedSpecialistBook(String isbn, String title, String topic) {
		super(isbn, title);
		this.topic = topic;
	}
	public String getTopic() {
		return topic;
	}
	
}
