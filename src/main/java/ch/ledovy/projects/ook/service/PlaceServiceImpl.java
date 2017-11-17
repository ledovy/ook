package ch.ledovy.projects.ook.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ledovy.projects.ook.model.Place;
import ch.ledovy.projects.ook.model.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	private PlaceRepository repo;
	
	@Autowired
	public PlaceServiceImpl(final PlaceRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public Collection<Place> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Place save(final Place item) {
		return repo.save(item);
	}
	
	@Override
	public void delete(final long id) {
		repo.delete(id);
	}
	
	@Override
	public Place load(final long id) {
		return repo.getOne(id);
	}
	
	@Override
	public Place create() {
		return new Place();
	}
	
}
