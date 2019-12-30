package com.bharath.location.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.location.entities.Location;
import com.bharath.location.repos.LocationRepository;
import com.bharath.location.service.LocationService;
import com.bharath.location.util.EmailUtil;
import com.bharath.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private LocationRepository repository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ReportUtil reportUtil;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";

	}

	@PostMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

		location.setCode(location.getCode().toUpperCase());

		location.setName(location.getName().toUpperCase());

		Location locationSaved = locationService.saveLocation(location);

		String msg = "location saved with id" + locationSaved.getId();

		modelMap.addAttribute("msg", msg);

		/*
		 * util.senEmail("kaushikkumar121292@gmail.com", "testingEmailService",
		 * "Hi from my App222");
		 */

		return "createLocation";

	}

	@GetMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {

		List<Location> locations = locationService.getAllLocations();

		modelMap.addAttribute("locations", locations);

		return "displayLocations";
	}

	@GetMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {

		locationService.deleteLocation(locationService.getLocationById(id));

		List<Location> locations = locationService.getAllLocations();

		modelMap.addAttribute("locations", locations);

		return "displayLocations";

	}

	@GetMapping("/showUpdate ")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {

		modelMap.addAttribute("location", locationService.getLocationById(id));

		return "updateLocation";

	}

	@PostMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

		location.setCode(location.getCode().toUpperCase());

		location.setName(location.getName().toUpperCase());

		locationService.updateLocation(location);

		List<Location> locations = locationService.getAllLocations();

		modelMap.addAttribute("locations", locations);

		/*
		 * util.senEmail("kaushikkumar121292@gmail.com", "testingEmailService",
		 * "location updated");
		 */
		return "displayLocations";

	}

	@GetMapping("/generateReport")

	public String generateReport() {

		String path = sc.getRealPath("/");

		
		  Session currentSession = entityManager.unwrap(Session.class);
		  
		  List<Object[]> dataByEntitymanager = currentSession.
		  createQuery("select type,count(type) from Location group by type")
		  .getResultList();
		 
		
		List<Object[]> data = repository.findTypeAndTypeCount(); 

		reportUtil.generatePieChart(path, dataByEntitymanager);

		return "report";

	}

}
