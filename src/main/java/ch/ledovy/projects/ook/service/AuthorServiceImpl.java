package ch.ledovy.projects.ook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ledovy.projects.ook.model.Author;
import ch.ledovy.projects.ook.model.AuthorRepository;
import ch.ledovy.sewer.data.service.AbstractCrudService;

@Service
public class AuthorServiceImpl extends AbstractCrudService<Author, AuthorParameter, AuthorRepository> implements AuthorService {
	
	@Autowired
	public AuthorServiceImpl(final AuthorRepository authorRepo) {
		super(Author.class, authorRepo);
	}
	
	@Override
	public List<Author> applyFindByParameter(final AuthorParameter parameter) {
		return repository.findByNameLikeIgnoreCase(sanitizeParameter(parameter).getName());
	}
	
	@Override
	public int applyCountByParameter(final AuthorParameter parameter) {
		return repository.countByNameLikeIgnoreCase(sanitizeParameter(parameter).getName());
	}
	
	private AuthorParameter sanitizeParameter(final AuthorParameter p) {
		AuthorParameter parameter = p == null ? new AuthorParameter() : p;
		parameter.setName(sanitizeWildcards(parameter.getName()));
		return parameter;
	}
	
	@Override
	public Author createAuthor(final String name) {
		Author author = new Author();
		author.setName(name);
		author = save(author);
		return author;
	}
	
}
