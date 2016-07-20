package org.javacream.publishing.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("school")
public class SingleTableDiscriminatorValueSchoolBook extends SingleTableDiscriminatorValueStrategyBaseBook {

	private static final long serialVersionUID = 1L;
	private String subject;
	private int schoolYear;
	public String getSubject() {
		return subject;
	}
	public int getSchoolYear() {
		return schoolYear;
	}
	protected SingleTableDiscriminatorValueSchoolBook() {
		super();
	}
	public SingleTableDiscriminatorValueSchoolBook(String isbn, String title, String subject, int schoolYear) {
		super(isbn, title);
		this.subject = subject;
		this.schoolYear = schoolYear;
	}
}
