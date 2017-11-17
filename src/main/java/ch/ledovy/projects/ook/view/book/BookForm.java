package ch.ledovy.projects.ook.view.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.ledovy.projects.ook.model.Author;
import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.model.Place;
import ch.ledovy.projects.ook.service.AuthorService;
import ch.ledovy.projects.ook.service.PlaceService;
import ch.ledovy.sewer.data.view.form.AbstractForm;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class BookForm extends AbstractForm<Book> {
	
	private VerticalLayout root;
	private TextField title;
	private TextField isbn;
	private TextArea description;
	private ComboBox<Author> author;
	private ComboBox<Place> place;
	private DateField bookDate;
	
	@Autowired
	public BookForm(final Messages messages, final AuthorService authorService, final PlaceService placeService) {
		title = messages.registerCaption("view.books.form.title.caption", new TextField(), this);
		author = messages.registerCaption("view.books.form.author.caption", new ComboBox<>(), this);
		author.setNewItemHandler(name -> {
			Author newAuthor = authorService.createAuthor(name);
			author.setItems(authorService.findAll());
			author.setSelectedItem(newAuthor);
		});
		author.setItemCaptionGenerator(author -> author.getName());
		author.setItems(authorService.findAll());
		isbn = messages.registerCaption("view.books.form.isbn.caption", new TextField(), this);
		description = messages.registerCaption("view.books.form.description.caption", new TextArea(), this);
		place = messages.registerCaption("view.books.form.place.caption", new ComboBox<>(), this);
		place.setEmptySelectionAllowed(false);
		place.setItems(placeService.findAll());
		bookDate = messages.registerCaption("view.books.form.bookDate.caption", new DateField(), this);
		
		root = new VerticalLayout(title, new HorizontalLayout(author, isbn), description, place, bookDate);
		
		binder.forField(title).asRequired("Bitte Titel angeben").bind(Book::getTitle, Book::setTitle);
		binder.bind(author, Book::getAuthor, Book::setAuthor);
		binder.bind(isbn, Book::getIsbn, Book::setIsbn);
		binder.bind(description, Book::getDescription, Book::setDescription);
		binder.bind(place, Book::getPlace, Book::setPlace);
		binder.bind(bookDate, Book::getBookDate, Book::setBookDate);
	}
	
	@Override
	public Focusable getFirstFormField() {
		return title;
	}
	
	@Override
	public Component getForm() {
		return root;
	}
	
	@Override
	public Book newValue() {
		return new Book();
	}
	
}
