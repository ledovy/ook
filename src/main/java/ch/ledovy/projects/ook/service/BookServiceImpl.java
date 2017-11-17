package ch.ledovy.projects.ook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ledovy.projects.ook.model.AuthorRepository;
import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.model.BookRepository;
import ch.ledovy.projects.ook.model.PlaceRepository;
import ch.ledovy.sewer.data.service.AbstractCrudService;

@Service
public class BookServiceImpl extends AbstractCrudService<Book, BookParameter, BookRepository> implements BookService {
	
	@Autowired
	public BookServiceImpl(final BookRepository bookRepo, final AuthorRepository authorRepo, final PlaceRepository placeRepo) {
		super(Book.class, bookRepo);
	}
	
	@Override
	public List<Book> applyFindByParameter(final BookParameter parameter) {
		BookParameter p = sanitizeParameter(parameter);
		return repository.findByTitleLikeIgnoreCaseAndIsbnLike(p.getTitle(), p.getIsbn());
	}
	
	@Override
	public int applyCountByParameter(final BookParameter parameter) {
		BookParameter p = sanitizeParameter(parameter);
		return repository.countByTitleLikeIgnoreCaseAndIsbnLike(p.getTitle(), p.getIsbn());
	}
	
	private BookParameter sanitizeParameter(final BookParameter p) {
		BookParameter parameter = p == null ? new BookParameter() : p;
		parameter.setTitle(sanitizeWildcards(parameter.getTitle()));
		parameter.setIsbn(sanitizeWildcards(parameter.getIsbn()));
		return parameter;
	}
}
