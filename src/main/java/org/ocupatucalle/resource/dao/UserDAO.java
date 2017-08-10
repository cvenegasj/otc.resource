package org.ocupatucalle.resource.dao;

import org.ocupatucalle.resource.domain.User;

public interface UserDAO extends GenericDAO<User, Integer> {

	User findByEmail(String email);

}
