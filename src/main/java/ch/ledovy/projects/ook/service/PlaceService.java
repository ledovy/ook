package ch.ledovy.projects.ook.service;

import java.util.Collection;

import ch.ledovy.projects.ook.model.Place;
import ch.ledovy.sewer.data.service.CrudService;

public interface PlaceService extends CrudService<Place> {
	
	Collection<Place> findAll();
	
}
