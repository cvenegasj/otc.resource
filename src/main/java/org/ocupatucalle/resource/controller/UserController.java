package org.ocupatucalle.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	
	@RequestMapping(value = "/api-public/public", method = RequestMethod.GET)
	public String out() {
        return "Hello from public side.";
    }
	
	@RequestMapping(value = "/api/secure", method = RequestMethod.GET)
	public String secure() {
        return "Hello from secure side.";
    }

}
