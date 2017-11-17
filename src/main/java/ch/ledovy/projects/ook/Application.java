package ch.ledovy.projects.ook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.vaadin.spring.events.annotation.EnableEventBus;

import ch.ledovy.projects.ook.model.Book;
import ch.ledovy.projects.ook.model.BookRepository;
import ch.ledovy.sewer.security.model.User;
import ch.ledovy.sewer.security.model.UserRepository;

@EnableJpaRepositories(basePackageClasses = { UserRepository.class, BookRepository.class }, enableDefaultTransactions = true)
@EntityScan(basePackageClasses = { User.class, Book.class })
@EnableEventBus
@SpringBootApplication(scanBasePackages = { "ch.ledovy.sewer", "ch.ledovy.projects.ook" })
public class Application extends SpringBootServletInitializer {
	
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
	
	@Bean
	public FilterRegistrationBean registerOpenEntityManagerInViewFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(5);
		return registrationBean;
	}
}
