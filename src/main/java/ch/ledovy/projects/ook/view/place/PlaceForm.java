package ch.ledovy.projects.ook.view.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.ledovy.projects.ook.model.Place;
import ch.ledovy.sewer.data.view.form.AbstractForm;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class PlaceForm extends AbstractForm<Place> {
	
	private VerticalLayout form;
	private TextField name;
	
	@Autowired
	public PlaceForm(final Messages messages) {
		name = messages.registerCaption("view.place.form.name.caption", new TextField(), this);
		
		form = new VerticalLayout(name);
		
		binder.bind(name, Place::getName, Place::setName);
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
	public Place newValue() {
		return new Place();
	}
	
}
