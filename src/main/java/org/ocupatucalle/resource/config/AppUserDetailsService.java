package org.ocupatucalle.resource.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.ocupatucalle.resource.dao.UserDAO;
import org.ocupatucalle.resource.domain.Role;
import org.ocupatucalle.resource.domain.User;
import org.ocupatucalle.resource.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User does not exist.");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				translate(user.getUserRoles()));
	}

	private Collection<? extends GrantedAuthority> translate(Set<UserRole> rolesUser) {
		List<Role> roles = new ArrayList<Role>();
		for (UserRole roleUser : rolesUser) {
			roles.add(roleUser.getRole());
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			String name = role.getName().toUpperCase();
			if (!name.startsWith("ROLE_")) {
				name = "ROLE_" + name;
			}
			authorities.add(new SimpleGrantedAuthority(name));
		}
		return authorities;
	}

}
