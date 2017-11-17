package ch.ledovy.projects.ook.view.author;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.ledovy.projects.ook.model.Author;
import ch.ledovy.sewer.data.view.form.AbstractForm;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class AuthorForm extends AbstractForm<Author> {
	
	private TextField name;
	private DateField birthday;
	private Messages messages;
	private AbstractOrderedLayout form;
	
	@Autowired
	public AuthorForm(final Messages messages) {
		this.messages = messages;
		name = messages.registerCaption("view.author.form.name.caption", new TextField(), this);
		birthday = messages.registerCaption("view.author.form.birthday.caption", new DateField(), this);
		
		form = new VerticalLayout(name, birthday);
		
		binder.forField(name).asRequired("Name fehlt").bind(Author::getName, Author::setName);
		binder.forField(birthday).bind(Author::getBirthday, Author::setBirthday);
	}
	
	@PreDestroy
	public void shutdown() {
		messages.unregister(this);
	}
	
	@Override
	public Focusable getFirstFormField() {
		return name;
	}
	
	@Override
	public Component getForm() {
		return form;
	}
	
	@Override
	public Author newValue() {
		return new Author();
	}
	
}
