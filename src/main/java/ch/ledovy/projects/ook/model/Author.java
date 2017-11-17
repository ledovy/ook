package ch.ledovy.projects.ook.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import ch.ledovy.sewer.data.model.AbstractEntity;

@Entity
public class Author extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;
	private LocalDate birthday;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
	}
	
}
