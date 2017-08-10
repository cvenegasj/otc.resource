package org.ocupatucalle.resource.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.ocupatucalle.resource.dao.DateoDAO;
import org.ocupatucalle.resource.dao.ProjectDAO;
import org.ocupatucalle.resource.dao.RoleDAO;
import org.ocupatucalle.resource.dao.UserDAO;
import org.ocupatucalle.resource.domain.Dateo;
import org.ocupatucalle.resource.domain.Project;
import org.ocupatucalle.resource.domain.User;
import org.ocupatucalle.resource.domain.UserRole;
import org.ocupatucalle.resource.dto.DateoDTO;
import org.ocupatucalle.resource.dto.ProjectDTO;
import org.ocupatucalle.resource.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api-public")
public class GeneralPublicController {

	private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy h:mm a");
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat monthYearFormatter = new SimpleDateFormat("MMMMM yyyy", new Locale("es","ES"));
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DateoDAO dateoDAO;
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;

	@RequestMapping(value = "/dateos", method = RequestMethod.GET)
	public List<DateoDTO> findAllDateos() {
		List<DateoDTO> returnList = new ArrayList<DateoDTO>();
		for (Dateo d : dateoDAO.findAll()) {
			returnList.add(convertToDTO(d));
		}

		return returnList;
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public List<ProjectDTO> findAllProjects() {
		List<ProjectDTO> returnList = new ArrayList<ProjectDTO>();
		for (Project p : projectDAO.findAll()) {
			returnList.add(convertToDTO(p));
		}

		return returnList;
	}
	
	@RequestMapping(value = "/dateos/{idDateo}", method = RequestMethod.GET)
    public DateoDTO findOneDateo(@PathVariable("idDateo") Integer idDateo) {
		Dateo d = dateoDAO.findById(idDateo);
		
		return convertToDTO(d);
	}
	
	@RequestMapping(value = "/projects/{idProject}", method = RequestMethod.GET)
    public ProjectDTO findOneProject(@PathVariable("idProject") Integer idProject) {
		Project p = projectDAO.findById(idProject);
		
		return convertToDTO(p);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createUser(@RequestBody UserDTO userDTO) throws ParseException {
		User user = convertToEntity(userDTO);
		// encrypt password
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		// assign role
		UserRole ur = new UserRole();
		ur.setRole(roleDAO.findById(3)); // ROLE_USER in database
		ur.setUser(user);

		user.getUserRoles().add(ur);
		User createdUser = userDAO.makePersistent(user);
		
		return convertToDTO(createdUser);
	}
	
	private DateoDTO convertToDTO(Dateo dateo) {
		DateoDTO dDTO = new DateoDTO();
		dDTO.setIdDateo(dateo.getIdDateo());
		dDTO.setAddress(dateo.getAddress());
		dDTO.setDescription(dateo.getDescription());
		dDTO.setLatitude(dateo.getLatitude());
		dDTO.setLongitude(dateo.getLongitude());
		dDTO.setTag1(dateo.getTag1());
		dDTO.setTag2(dateo.getTag2());
		dDTO.setTag3(dateo.getTag3());
		dDTO.setPhotoUrl(dateo.getPhotoUrl());	
		dDTO.setCreationDateTime(dateTimeFormatter.format(dateo.getCreationDateTime()));
		dDTO.setUserId(dateo.getUser().getIdUser());
		dDTO.setUserFirstName(dateo.getUser().getFirstName());
		dDTO.setUserLastName(dateo.getUser().getLastName());
		
		return dDTO;
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
	
	private UserDTO convertToDTO(User user) {
		UserDTO uDTO = new UserDTO();
		uDTO.setFirstName(user.getFirstName());
		uDTO.setLastName(user.getLastName());
		uDTO.setEmail(user.getEmail());
		uDTO.setOrganization(uDTO.getOrganization());
		
		return uDTO;
	}
	
	private User convertToEntity(UserDTO uDTO) {
		User u = new User();
		u.setFirstName(uDTO.getFirstName());
		u.setLastName(uDTO.getLastName());
		u.setEmail(uDTO.getEmail());
		u.setOrganization(uDTO.getOrganization());
		
		return u;
	}
	
}
