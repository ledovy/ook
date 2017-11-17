package ch.ledovy.projects.ook.view.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

import ch.ledovy.projects.ook.model.Author;
import ch.ledovy.projects.ook.service.AuthorService;
import ch.ledovy.sewer.data.view.form.DefaultEditor;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class AuthorEditor extends DefaultEditor<Author> {
	@Autowired
	public AuthorEditor(final AuthorForm form, final AuthorService service, final Messages messages) {
		super(form, messages);
		messages.registerCaption("view.author.editor.caption", this, this);
	}
	
}
