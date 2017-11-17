package ch.ledovy.projects.ook.view.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

import ch.ledovy.projects.ook.model.Place;

@SpringComponent
@PrototypeScope
public class PlaceGrid extends Grid<Place> {
	@Autowired
	public PlaceGrid() {
		super(Place.class);
	}
}
