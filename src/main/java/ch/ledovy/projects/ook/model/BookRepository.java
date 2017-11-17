package ch.ledovy.projects.ook.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleLikeIgnoreCaseAndIsbnLike(String title, String isbn);
	
	int countByTitleLikeIgnoreCaseAndIsbnLike(String title, String isbn);
}
