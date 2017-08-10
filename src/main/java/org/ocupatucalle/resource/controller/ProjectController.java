package org.ocupatucalle.resource.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

import org.ocupatucalle.resource.dao.ProjectDAO;
import org.ocupatucalle.resource.dao.UserDAO;
import org.ocupatucalle.resource.domain.Project;
import org.ocupatucalle.resource.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/projects")
public class ProjectController {
	
	private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy h:mm a");
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat monthYearFormatter = new SimpleDateFormat("MMMMM yyyy", new Locale("es","ES"));
	
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) throws ParseException {
		Project project = convertToEntity(projectDTO);
		// set User
		project.setUser(userDAO.findByEmail(projectDTO.getUserEmail()));
		// set creation datetime 
        Instant now = Instant.now();
        ZonedDateTime zdtLima = now.atZone(ZoneId.of("GMT-05:00"));
        project.setCreationDateTime(Date.from(zdtLima.toInstant())); 
	
		Project createdProject = projectDAO.makePersistent(project);
		
		return convertToDTO(createdProject);
	}
	
	private ProjectDTO convertToDTO(Project project) {
		ProjectDTO pDTO = new ProjectDTO();
		pDTO.setIdProject(project.getIdProject());
		pDTO.setAddress(project.getAddress());
		pDTO.setName(project.getName());
		pDTO.setDescription(project.getDescription());
		pDTO.setIsOccupied(project.getIsOccupied());
		pDTO.setIsToBeOccupied(project.getIsToBeOccupied());
		pDTO.setIsInConflict(project.getIsInConflict());
		pDTO.setIntervenedSpace(project.getIntervenedSpace());
		pDTO.setArea(project.getArea());
		pDTO.setStartPeriod(monthYearFormatter.format(project.getStartPeriod()));
		pDTO.setEndPeriod(monthYearFormatter.format(project.getEndPeriod()));
		pDTO.setInaugurationDate(dateFormatter.format(project.getInaugurationDate()));
		pDTO.setElements(project.getElements());
		pDTO.setExecution(project.getExecution());
		pDTO.setDonations(project.getDonations());
		pDTO.setMaterials(project.getMaterials());
		pDTO.setPhotoUrl(project.getPhotoUrl());
		pDTO.setProcessDescription(project.getProcessDescription());
		pDTO.setLatitude(project.getLatitude());
		pDTO.setLongitude(project.getLongitude());
		pDTO.setCreationDateTime(dateTimeFormatter.format(project.getCreationDateTime()));
		pDTO.setUserId(project.getUser().getIdUser());
		pDTO.setUserFirstName(project.getUser().getFirstName());
		pDTO.setUserLastName(project.getUser().getLastName());
		
		return pDTO;
	}
	
	private Project convertToEntity(ProjectDTO pDTO) throws ParseException {
		Project p = new Project();
		p.setAddress(pDTO.getAddress());
		p.setName(pDTO.getName());
		p.setDescription(pDTO.getDescription());
		p.setIsOccupied(pDTO.getIsOccupied());
		p.setIsToBeOccupied(pDTO.getIsToBeOccupied());
		p.setIsInConflict(pDTO.getIsInConflict());
		p.setIntervenedSpace(pDTO.getIntervenedSpace());
		p.setArea(pDTO.getArea());
		p.setStartPeriod(dateFormatter.parse(pDTO.getStartPeriod()));
		p.setEndPeriod(dateFormatter.parse(pDTO.getEndPeriod()));
		p.setInaugurationDate(dateFormatter.parse(pDTO.getInaugurationDate()));
		p.setElements(pDTO.getElements());
		p.setExecution(pDTO.getExecution());
		p.setDonations(pDTO.getDonations());
		p.setMaterials(pDTO.getMaterials());
		p.setPhotoUrl(pDTO.getPhotoUrl());
		p.setProcessDescription(pDTO.getProcessDescription());
		p.setLatitude(pDTO.getLatitude());
		p.setLongitude(pDTO.getLongitude());
		
		return p;
	}

}
