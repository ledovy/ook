package ch.ledovy.projects.ook.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import ch.ledovy.sewer.data.model.AbstractEntity;

@Entity
public class Place extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
}
