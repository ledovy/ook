package ch.ledovy.projects.ook;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

import ch.ledovy.projects.ook.view.book.BooksView;
import ch.ledovy.sewer.navigation.Desktop;
import ch.ledovy.sewer.navigation.Navigator;
import ch.ledovy.sewer.navigation.ThemeProvider;
import ch.ledovy.sewer.security.NoAccessView;

@SpringUI
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
@Title("Ook - ${app.environment}")
@Push(transport = Transport.LONG_POLLING, value = PushMode.AUTOMATIC)
public class OokUI extends UI {
	private static final long serialVersionUID = 1L;
	private Navigator navigator;
	
	@Autowired
	public OokUI(final Navigator navigator, final SpringViewProvider viewProvider, final ThemeProvider themeProvider, final BeanFactory factory) {
		this.navigator = navigator;
		
		themeProvider.registerTheme(OokTheme.THEME_NAME);
		
		Desktop desktop = factory.getBean(Desktop.class);
		this.navigator.init(this, desktop.getTarget());
		navigator.setDefaultView(BooksView.NAME);
		viewProvider.setAccessDeniedViewClass(NoAccessView.class);
		this.navigator.addProvider(viewProvider);
		setContent(desktop);
	}
	
	@Override
	protected void init(final VaadinRequest vaadinRequest) {
		navigator.navigateToDefaultView();
	}
}
