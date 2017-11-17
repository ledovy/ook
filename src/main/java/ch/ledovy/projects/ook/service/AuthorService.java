package ch.ledovy.projects.ook.service;

import ch.ledovy.projects.ook.model.Author;
import ch.ledovy.sewer.data.model.ParameterRepository;
import ch.ledovy.sewer.data.service.CrudService;

public interface AuthorService extends CrudService<Author>, ParameterRepository<Author, AuthorParameter> {
	
	Author createAuthor(String name);
	
}
