package ch.ledovy.projects.ook.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	List<Author> findByNameLikeIgnoreCase(String name);
	
	int countByNameLikeIgnoreCase(String name);
	
}
