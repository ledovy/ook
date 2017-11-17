package ch.ledovy.projects.ook.view.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.service.BookService;
import ch.ledovy.sewer.data.view.ValueConsumer;
import ch.ledovy.sewer.data.view.form.FormActivationListener;
import ch.ledovy.sewer.data.view.form.FormDeactivationListener;
import ch.ledovy.sewer.i18n.Messages;
import ch.ledovy.sewer.log.HasLogger;

@SpringComponent
@PrototypeScope
public class IsbnWizard extends Window implements ValueConsumer<List<Book>>, HasLogger, FormActivationListener, FormDeactivationListener {
	private Grid<Book> searchResult;
	private State state = State.CLEAN;
	private BookForm form;
	private Panel stateView;
	private BookService service;
	
	private enum State {
		CLEAN, SELECTION, EDIT, SAVE;
	}
	
	@Autowired
	public IsbnWizard(final BookForm form, final BookService service, final Messages messages) {
		this.form = form;
		this.service = service;
		searchResult = new Grid<>(Book.class);
		stateView = new Panel();
		
		Button next = messages.registerCaption("view.default.wizard.next.caption", new Button(), this);
		next.addClickListener(event -> {
			next();
			refresh();
		});
		setContent(new VerticalLayout(stateView, next));
	}
	
	private void next() {
		switch (state) {
			case CLEAN:
				state = State.SELECTION;
			case SELECTION:
				Book book = searchResult.getSelectedItems().iterator().next();
				form.setValue(book);
				state = State.EDIT;
				break;
			case EDIT:
				try {
					service.save(form.getValue());
				} catch (ValidationException e) {
					getLogger().warn(e.getMessage(), e);
					Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
				getUI().removeWindow(this);
				state = State.SAVE;
			default:
				break;
		}
	}
	
	private void refresh() {
		switch (state) {
			case SELECTION:
				stateView.setContent(searchResult);
				break;
			case EDIT:
				stateView.setContent(form.getForm());
				break;
			default:
				throw new IllegalStateException(state.toString());
		}
	}
	
	@Override
	public void setValue(final List<Book> books) {
		searchResult.setItems(books);
		state = State.SELECTION;
		refresh();
	}

	@Override
	public void formActivated() {
		open();
	}
	
	@Override
	public void formDeactivated() {
		close();
	}
	
	public void open() {
		UI.getCurrent().addWindow(this);
	}
	
}
