package ch.ledovy.projects.ook.view.book;

import static ch.ledovy.sewer.action.ExecutorFactory.create;
import static ch.ledovy.sewer.action.MenuFactory.forMenuBar;
import static ch.ledovy.sewer.data.view.CrudActions.createAddAction;
import static ch.ledovy.sewer.data.view.CrudActions.createCancelAction;
import static ch.ledovy.sewer.data.view.CrudActions.createDeleteAction;
import static ch.ledovy.sewer.data.view.CrudActions.createEditAction;
import static ch.ledovy.sewer.data.view.CrudActions.createSaveAction;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.contextmenu.ContextMenu;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.service.BookParameter;
import ch.ledovy.projects.ook.service.BookService;
import ch.ledovy.projects.ook.service.IsbnService;
import ch.ledovy.sewer.action.Menu;
import ch.ledovy.sewer.action.MenuFactory;
import ch.ledovy.sewer.data.view.filter.FilterClearButton;
import ch.ledovy.sewer.data.view.filter.FilterFactory;
import ch.ledovy.sewer.data.view.filter.FilterPresenter;
import ch.ledovy.sewer.data.view.filter.FilterSearchButton;
import ch.ledovy.sewer.i18n.Messages;

@UIScope
@SpringView(name = BooksView.NAME)
public class BooksView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "books";
	private Messages messages;
	
	@Autowired
	public BooksView(final BookEditor editor, final BooksGrid grid, final IsbnWizard isbnWizard, final Messages messages, final BookService service, final IsbnService isbnService) {
		this.messages = messages;
		HorizontalLayout filterLayout = createFilter(grid, messages);
		
		// Form
		createSaveAction(create(editor.getSave()), grid, editor.getForm(), service).withFormDeactivationListener(editor);
		createCancelAction(create(editor.getCancel()), editor.getForm()).withFormDeactivationListener(editor);
		
		// Menu
		MenuBar menu = new MenuBar();
		Menu subMenu = forMenuBar(menu, messages).addMenu("view.books.menu.actions");
		createAddAction(subMenu.addItem("view.books.menu.add"), editor.getForm(), service).withFormActivationListener(editor);
		createEditAction(subMenu.addItem("view.books.menu.edit"), grid, editor.getForm(), service).withFormActivationListener(editor);
		createDeleteAction(subMenu.addItem("view.books.menu.delete"), grid, service);
		
		// ContextMenu
		Menu contextMenu = MenuFactory.forContextMenu(new ContextMenu(grid, true), messages);
		createAddAction(contextMenu.addItem("view.books.menu.add"), editor.getForm(), service).withFormActivationListener(editor);
		createEditAction(contextMenu.addItem("view.books.menu.edit"), grid, editor.getForm(), service).withFormActivationListener(editor);
		createDeleteAction(contextMenu.addItem("view.books.menu.delete"), grid, service);
		
		// ISBN-Service
		TextField isbnField = messages.registerPlaceholder("view.books.isbn.input", new TextField(), this);
		Button isbnSearch = messages.registerCaption("view.books.isbn.search", new Button(), this);
		createAddAction(create(isbnSearch), isbnWizard, () -> {
			String isbn = isbnField.getValue();
			return isbnService.search(isbn);
		}).withFormActivationListener(isbnWizard);
		
		// Layout
		setSizeFull();
		addComponent(filterLayout);
		addComponent(new HorizontalLayout(isbnField, isbnSearch));
		addComponent(new HorizontalLayout(menu));
		addComponent(grid);
	}
	
	private HorizontalLayout createFilter(final BooksGrid grid, final Messages messages) {
		// Filter
		BooksFilter filterGui = new BooksFilter(messages);
		FilterPresenter<Book, BookParameter> filter = FilterFactory.createBackendFilter(filterGui, grid.getDataProvider());
		Button search = this.messages.registerCaption("view.model.button.filter.search", new FilterSearchButton(filter), this);
		Button clear = this.messages.registerCaption("view.model.button.filter.clear", new FilterClearButton(filter), this);
		filter.clear();
		HorizontalLayout filterLayout = new HorizontalLayout(filterGui.getForm(), new HorizontalLayout(clear, search));
		return filterLayout;
	}
	
	@PreDestroy
	public void shutdown() {
		messages.unregister(this);
	}
}
