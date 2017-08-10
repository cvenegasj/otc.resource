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
@Table(name = "Project")
public class Project implements java.io.Serializable {

	private Integer idProject;
	private String address;
	private String name;
	private String description;
	private Boolean isOccupied;
	private Boolean isToBeOccupied;
	private Boolean isInConflict;
	private String intervenedSpace;
	private Integer area;
	private Date startPeriod;
	private Date endPeriod;
	private Date inaugurationDate;
	private String elements;
	private String execution;
	private String donations;
	private String materials;
	private String photoUrl;
	private String processDescription;
	private String latitude;
	private String longitude;
	private Date creationDateTime;
	private User user;

	public Project() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idProject", unique = true, nullable = false)
	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "isOccupied", nullable = false)
	public Boolean getIsOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	@Column(name = "isToBeOccupied", nullable = false)
	public Boolean getIsToBeOccupied() {
		return isToBeOccupied;
	}

	public void setIsToBeOccupied(Boolean isToBeOccupied) {
		this.isToBeOccupied = isToBeOccupied;
	}

	@Column(name = "isInConflict", nullable = false)
	public Boolean getIsInConflict() {
		return isInConflict;
	}

	public void setIsInConflict(Boolean isInConflict) {
		this.isInConflict = isInConflict;
	}

	@Column(name = "intervenedSpace", nullable = false)
	public String getIntervenedSpace() {
		return intervenedSpace;
	}

	public void setIntervenedSpace(String intervenedSpace) {
		this.intervenedSpace = intervenedSpace;
	}

	@Column(name = "area", nullable = false)
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startPeriod", nullable = false)
	public Date getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endPeriod", nullable = false)
	public Date getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "inaugurationDate", nullable = false)
	public Date getInaugurationDate() {
		return inaugurationDate;
	}

	public void setInaugurationDate(Date inaugurationDate) {
		this.inaugurationDate = inaugurationDate;
	}

	@Column(name = "elements", nullable = false)
	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}

	@Column(name = "execution", nullable = false)
	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	@Column(name = "donations", nullable = false)
	public String getDonations() {
		return donations;
	}

	public void setDonations(String donations) {
		this.donations = donations;
	}

	@Column(name = "materials", nullable = false)
	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	@Column(name = "photoUrl")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Column(name = "processDescription")
	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
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
