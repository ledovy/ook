package ch.ledovy.projects.ook.view.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.service.BookService;
import ch.ledovy.sewer.data.view.form.DefaultEditor;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class BookEditor extends DefaultEditor<Book> {
	@Autowired
	public BookEditor(final BookForm form, final BookService service, final Messages messages) {
		super(form, messages);
		messages.registerCaption("view.books.editor.caption", this, this);
	}
	
}
