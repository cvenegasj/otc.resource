package org.ocupatucalle.resource.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "UserRole")
public class UserRole implements java.io.Serializable {

	private Integer idUserRole;
	private Role role;
	private User user;

	public UserRole() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUserRole", unique = true, nullable = false)
	public Integer getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(Integer idUserRole) {
		this.idUserRole = idUserRole;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRole", nullable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUser", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
