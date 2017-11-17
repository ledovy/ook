package ch.ledovy.projects.ook.view.book;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import ch.ledovy.projects.ook.service.BookParameter;
import ch.ledovy.sewer.data.view.form.AbstractForm;
import ch.ledovy.sewer.i18n.Messages;

public class BooksFilter extends AbstractForm<BookParameter> {
	
	private AbstractOrderedLayout root;
	private TextField title;
	private TextField isbn;
	
	public BooksFilter(final Messages messages) {
		title = messages.registerCaption("view.books.form.title.caption", new TextField(), this);
		isbn = messages.registerCaption("view.books.form.isbn.caption", new TextField(), this);
		
		root = new HorizontalLayout(title, isbn);
		
		binder.bind(title, BookParameter::getTitle, BookParameter::setTitle);
		binder.bind(isbn, BookParameter::getIsbn, BookParameter::setIsbn);
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
	public BookParameter newValue() {
		return new BookParameter();
	}
	
}
