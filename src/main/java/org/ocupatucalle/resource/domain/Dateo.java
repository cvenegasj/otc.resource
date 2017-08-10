package org.ocupatucalle.resource.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "Dateo")
public class Dateo implements java.io.Serializable {

	private Integer idDateo;
	private String address;
	private String description;
	private String latitude;
	private String longitude;
	private String tag1;
	private String tag2;
	private String tag3;
	private String photoUrl;
	private Date creationDateTime;
	private User user;

	public Dateo() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idDateo", unique = true, nullable = false)
	public Integer getIdDateo() {
		return idDateo;
	}

	public void setIdDateo(Integer idDateo) {
		this.idDateo = idDateo;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "latitude", nullable = false)
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", nullable = false)
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "tag1")
	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	@Column(name = "tag2")
	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	@Column(name = "tag3")
	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}

	@Column(name = "photoUrl")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDateTime", nullable = false)
	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
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
