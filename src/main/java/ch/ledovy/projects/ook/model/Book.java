package ch.ledovy.projects.ook.model;

import java.time.LocalDate;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ch.ledovy.sewer.data.model.AbstractEntity;

@Entity
public class Book extends AbstractEntity {
	private static final long serialVersionUID = 2L;
	private String title;
	private String description;
	private String isbn;
	private Author author;
	private Place place;
	private String publisher;
	private String edition;
	private LocalDate bookDate;
	private LocalDate editionDate;
	private Locale language;
	
	@Column(nullable = false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(final String title) {
		this.title = title;
	}
	
	@Column(length = 5000)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(final String description) {
		this.description = description;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}
	
	@ManyToOne
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(final Author author) {
		this.author = author;
	}
	
	@ManyToOne
	public Place getPlace() {
		return place;
	}
	
	public void setPlace(final Place place) {
		this.place = place;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(final String publisher) {
		this.publisher = publisher;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public void setEdition(final String edition) {
		this.edition = edition;
	}
	
	public LocalDate getBookDate() {
		return bookDate;
	}
	
	public void setBookDate(final LocalDate bookDate) {
		this.bookDate = bookDate;
	}
	
	public LocalDate getEditionDate() {
		return editionDate;
	}
	
	public void setEditionDate(final LocalDate editionDate) {
		this.editionDate = editionDate;
	}
	
	public Locale getLanguage() {
		return language;
	}
	
	public void setLanguage(final Locale language) {
		this.language = language;
	}
	
}
