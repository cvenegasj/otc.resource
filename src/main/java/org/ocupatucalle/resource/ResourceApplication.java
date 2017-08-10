package org.ocupatucalle.resource;

import org.ocupatucalle.resource.dao.DateoDAO;
import org.ocupatucalle.resource.dao.ProjectDAO;
import org.ocupatucalle.resource.dao.RoleDAO;
import org.ocupatucalle.resource.dao.UserDAO;
import org.ocupatucalle.resource.daoimpl.DateoDAOImpl;
import org.ocupatucalle.resource.daoimpl.ProjectDAOImpl;
import org.ocupatucalle.resource.daoimpl.RoleDAOImpl;
import org.ocupatucalle.resource.daoimpl.UserDAOImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResourceApplication {
	
	// For Tomcat deployment
	/*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ResourceApplication.class);
    }*/
		
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

	// ========== DAO beans ==========

	@Bean(name = "userDAO")
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	@Bean(name = "dateoDAO")
	public DateoDAO getDateoDAO() {
		return new DateoDAOImpl();
	}
	
	@Bean(name = "projectDAO")
	public ProjectDAO getProjectDAO() {
		return new ProjectDAOImpl();
	}

	@Bean(name = "roleDAO")
	public RoleDAO getRoleDAO() {
		return new RoleDAOImpl();
	}
	
}
