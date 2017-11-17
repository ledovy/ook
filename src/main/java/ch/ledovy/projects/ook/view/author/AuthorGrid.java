package ch.ledovy.projects.ook.view.author;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

import ch.ledovy.projects.ook.model.Author;
import ch.ledovy.projects.ook.service.AuthorParameter;
import ch.ledovy.projects.ook.service.AuthorService;
import ch.ledovy.sewer.data.view.filter.FilterableRepositoryProvider;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class AuthorGrid extends Grid<Author> {
	
	private Messages messages;
	private FilterableRepositoryProvider<Author, AuthorParameter> provider;
	
	@Autowired
	private AuthorGrid(final Messages messages, final AuthorService service) {
		super(Author.class);
		this.messages = messages;
		provider = new FilterableRepositoryProvider<>(service);
		setDataProvider(provider);
		setSizeFull();
		messages.registerCaption("view.author.grid.caption", this, this);
		provider.refreshAll();
	}
	
	@PreDestroy
	public void shutdown() {
		messages.unregister(this);
	}
	
	@Override
	public DataProvider<Author, ?> getDataProvider() {
		return provider;
	}
}
