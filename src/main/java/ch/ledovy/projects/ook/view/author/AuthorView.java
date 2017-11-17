package ch.ledovy.projects.ook.view.author;

import static ch.ledovy.sewer.action.ExecutorFactory.create;
import static ch.ledovy.sewer.action.MenuFactory.forMenuBar;
import static ch.ledovy.sewer.data.view.CrudActions.createAddAction;
import static ch.ledovy.sewer.data.view.CrudActions.createCancelAction;
import static ch.ledovy.sewer.data.view.CrudActions.createDeleteAction;
import static ch.ledovy.sewer.data.view.CrudActions.createEditAction;
import static ch.ledovy.sewer.data.view.CrudActions.createSaveAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.contextmenu.ContextMenu;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

import ch.ledovy.projects.ook.service.AuthorService;
import ch.ledovy.sewer.action.Menu;
import ch.ledovy.sewer.action.MenuFactory;
import ch.ledovy.sewer.i18n.Messages;

@UIScope
@SpringView(name = "authors")
public class AuthorView extends VerticalLayout implements View {
	@Autowired
	public AuthorView(final AuthorEditor editor, final AuthorGrid grid, final AuthorService service, final Messages messages) {
		// Form
		createSaveAction(create(editor.getSave()), grid, editor.getForm(), service).withFormDeactivationListener(editor);
		createCancelAction(create(editor.getCancel()), editor.getForm()).withFormDeactivationListener(editor);
		
		// Menu
		MenuBar menu = new MenuBar();
		Menu subMenu = forMenuBar(menu, messages).addMenu("view.author.menu.actions");
		createAddAction(subMenu.addItem("view.author.menu.add"), editor.getForm(), service).withFormActivationListener(editor);
		createEditAction(subMenu.addItem("view.author.menu.edit"), grid, editor.getForm(), service).withFormActivationListener(editor);
		createDeleteAction(subMenu.addItem("view.author.menu.delete"), grid, service);
		
		// ContextMenu
		Menu contextMenu = MenuFactory.forContextMenu(new ContextMenu(grid, true), messages);
		createAddAction(contextMenu.addItem("view.author.menu.add"), editor.getForm(), service).withFormActivationListener(editor);
		createEditAction(contextMenu.addItem("view.author.menu.edit"), grid, editor.getForm(), service).withFormActivationListener(editor);
		createDeleteAction(contextMenu.addItem("view.author.menu.delete"), grid, service);
		
		addComponent(menu);
		addComponent(grid);
	}
}
