package ch.ledovy.projects.ook.service;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.sewer.data.model.ParameterRepository;
import ch.ledovy.sewer.data.service.CrudService;

public interface BookService extends CrudService<Book>, ParameterRepository<Book, BookParameter> {
	
}
