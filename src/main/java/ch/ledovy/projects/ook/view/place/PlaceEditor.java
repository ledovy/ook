package ch.ledovy.projects.ook.view.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

import ch.ledovy.projects.ook.model.Place;
import ch.ledovy.sewer.data.view.form.DefaultEditor;
import ch.ledovy.sewer.i18n.Messages;

@SpringComponent
@PrototypeScope
public class PlaceEditor extends DefaultEditor<Place> {
	@Autowired
	public PlaceEditor(final PlaceForm form, final Messages messages) {
		super(form, messages);
	}
	
}
