package org.javacream.publishing.inheritance;

import javax.persistence.Entity;

@Entity
public class TablePerClassSchoolBook extends TablePerClassStrategyBaseBook {

	private static final long serialVersionUID = 1L;
	private String subject;
	private int schoolYear;
	public String getSubject() {
		return subject;
	}
	public int getSchoolYear() {
		return schoolYear;
	}
	protected TablePerClassSchoolBook() {
		super();
	}
	public TablePerClassSchoolBook(String isbn, String title, String subject, int schoolYear) {
		super(isbn, title);
		this.subject = subject;
		this.schoolYear = schoolYear;
	}
}
