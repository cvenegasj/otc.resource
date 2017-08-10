package org.ocupatucalle.resource.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.ocupatucalle.resource.dao.DateoDAO;
import org.ocupatucalle.resource.dao.UserDAO;
import org.ocupatucalle.resource.domain.Dateo;
import org.ocupatucalle.resource.dto.DateoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dateos")
public class DateoController {

	private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy h:mm a");
	
	@Autowired
	private DateoDAO dateoDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello from secure.";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public DateoDTO create(@RequestBody DateoDTO dateoDTO) {
		Dateo dateo = convertToEntity(dateoDTO);
		// User
		dateo.setUser(userDAO.findByEmail(dateoDTO.getUserEmail()));
		// set creation datetime 
        Instant now = Instant.now();
        ZonedDateTime zdtLima = now.atZone(ZoneId.of("GMT-05:00"));
        dateo.setCreationDateTime(Date.from(zdtLima.toInstant()));
	
		Dateo createdDateo = dateoDAO.makePersistent(dateo);
		
		return convertToDTO(createdDateo);
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
	
	private Dateo convertToEntity(DateoDTO dDTO) {
		Dateo d = new Dateo();
		d.setAddress(dDTO.getAddress());
		d.setDescription(dDTO.getDescription());
		d.setLatitude(dDTO.getLatitude());
		d.setLongitude(dDTO.getLongitude());
		d.setTag1(dDTO.getTag1());
		d.setTag2(dDTO.getTag2());
		d.setTag3(dDTO.getTag3());
		d.setPhotoUrl(dDTO.getPhotoUrl());
		// set creation datetime 
        Instant now = Instant.now();
        ZonedDateTime zdtLima = now.atZone(ZoneId.of("GMT-05:00"));
        d.setCreationDateTime(Date.from(zdtLima.toInstant())); 
		
		return d;
	}
	
}
