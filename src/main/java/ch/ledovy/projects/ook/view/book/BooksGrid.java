package ch.ledovy.projects.ook.view.book;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.service.BookParameter;
import ch.ledovy.projects.ook.service.BookService;
import ch.ledovy.sewer.data.view.filter.FilterableRepositoryProvider;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class BooksGrid extends Grid<Book> {
	private static final long serialVersionUID = 1L;
	private Messages messages;
	private FilterableRepositoryProvider<Book, BookParameter> provider;
	
	@Autowired
	public BooksGrid(final Messages messages, final BookService service) {
		super(Book.class);
		this.messages = messages;
		provider = new FilterableRepositoryProvider<>(service);
		setDataProvider(provider);
		setSizeFull();
		
		messages.registerCaption("view.books.grid.caption", this, this);
	}
	
	@PreDestroy
	public void shutdown() {
		messages.unregister(this);
	}
	
	@Override
	public FilterableRepositoryProvider<Book, BookParameter> getDataProvider() {
		return provider;
	}
}
