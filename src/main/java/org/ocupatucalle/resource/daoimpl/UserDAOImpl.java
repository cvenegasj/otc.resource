package org.ocupatucalle.resource.daoimpl;

import org.ocupatucalle.resource.dao.UserDAO;
import org.ocupatucalle.resource.domain.User;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

	@Transactional
	public User findByEmail(String email) {
		User user = null;
		user = (User) getSession().createQuery("from " + getDomainClassName() + " x where x.email = :email")
				.setString("email", email).setMaxResults(1).uniqueResult();
		return user;
	}

}
