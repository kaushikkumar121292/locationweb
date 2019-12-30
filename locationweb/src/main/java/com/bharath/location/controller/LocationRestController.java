package com.bharath.location.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.location.entities.Location;
import com.bharath.location.repos.LocationRepository;
import com.bharath.location.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationRestController {
	
	@Autowired
	LocationRepository repository;
	
	@Autowired
	private EntityManager entityManager;


	@GetMapping
	public List<Location> getAllLocations(){
		
		
		return repository.findAll();
		
	}
	
	
	  @GetMapping("/{id}") public Location getLocation(@PathVariable("id") int id)
	  {
	  
	  Session session = entityManager.unwrap(Session.class);
	  
	  
	  return session.get(Location.class, id);
	  
	  
	  }
	 
	
	@PostMapping
	public Location createLoaction(@RequestBody Location location){
	
		location.setCode(location.getCode().toUpperCase());

		location.setName(location.getName().toUpperCase());
		
		location.setType(location.getType().toUpperCase());
		
		return repository.save(location);
		
	}
	
	@PutMapping
	public Location updateLocation(@RequestBody Location location){
	
		location.setCode(location.getCode().toUpperCase());

		location.setName(location.getName().toUpperCase());
		
		location.setType(location.getType().toUpperCase());
		
		return repository.save(location);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteLoaction(@PathVariable("id") int id) {
		
		repository.deleteById(id);
		
	}

	
}
