package ch.ledovy.projects.ook;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventBus.UIEventBus;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import ch.ledovy.sewer.i18n.ComponentMessages;

@SpringComponent
@UIScope
public class OokMessages extends ComponentMessages {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageSource source;
	private UIEventBus eventBus;
	
	@Autowired
	public OokMessages(final EventBus.UIEventBus eventBus) {
		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("i18n/ook");
		this.source = source;
		this.eventBus = eventBus;
		this.eventBus.subscribe(this);
	}
	
	@PreDestroy
	public void shutdown() {
		this.eventBus.unsubscribe(this);
	}
	
	@Override
	protected MessageSource getSource() {
		return this.source;
	}
	
}
