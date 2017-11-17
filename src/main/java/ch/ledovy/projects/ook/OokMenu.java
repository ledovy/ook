package ch.ledovy.projects.ook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;

import ch.ledovy.projects.ook.view.author.AuthorView;
import ch.ledovy.projects.ook.view.book.BooksView;
import ch.ledovy.projects.ook.view.place.PlaceView;
import ch.ledovy.sewer.navigation.menu.Menu;
import ch.ledovy.sewer.navigation.menu.MenuEntry;
import ch.ledovy.sewer.security.view.LogoutView;

@SpringComponent
public class OokMenu implements Menu {
	private static final String MENU_HOME = "main.menu.home";
	
	private final List<String> menus = Arrays.asList(OokMenu.MENU_HOME);
	private final Map<String, List<MenuEntry>> menuEntries = new ConcurrentHashMap<>();
	
	public OokMenu() {
		this.menuEntries.put(OokMenu.MENU_HOME,
				Arrays.asList(new MenuEntry(OokMenu.MENU_HOME + ".books", VaadinIcons.BOOK, BooksView.class), new MenuEntry(OokMenu.MENU_HOME + ".author", VaadinIcons.BOOK, AuthorView.class),
						new MenuEntry(OokMenu.MENU_HOME + ".place", VaadinIcons.BOOK, PlaceView.class), new MenuEntry(OokMenu.MENU_HOME + ".logout", VaadinIcons.SIGN_OUT, LogoutView.class)));
	}
	
	@Override
	public List<String> getMenus() {
		return this.menus;
	}
	
	@Override
	public List<MenuEntry> getMenuEntries(final String menu) {
		if (this.menuEntries.containsKey(menu)) {
			return this.menuEntries.get(menu);
		} else {
			return Collections.emptyList();
		}
	}
	
}
