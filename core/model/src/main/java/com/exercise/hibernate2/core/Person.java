package com.exercise.hibernate2.core;

import org.hibernate.annotations.Type;
import org.hibernate.type.YesNoType;

import java.util.Set;
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import com.exercise.hibernate2.core.BooleanToStringConverter;

@Entity
@Table(name = "Person")
public class Person extends PersistentObject
{

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "middleName")
	private String middleName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "title")
	private String title;

	@Column(name = "birthDate")
	@Type(type="date")
	private Date birthDate;

	@Convert(converter=BooleanToStringConverter.class)
	@Column(name = "employed" )
	private Boolean employed;

	@Column(name = "gwa")
	private float gwa;

	@Column(name = "dateHired")
	@Type(type="date")
	private Date dateHired;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="personId")
	private Set<Contact> contacts;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="person_role", joinColumns={ @JoinColumn (name="personId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
	private Set<Role> roles;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address address;

	public Person(){}

	public Person(String firstName, String middleName,String lastName, String suffix, String title,
					Date birthDate,Boolean employed,float gwa, Date dateHired, Address address){

		this.firstName=firstName;
		this.middleName=middleName;
		this.lastName=lastName;
		this.suffix=suffix;
		this.title=title;
		this.birthDate=birthDate;
		this.employed=employed;
		this.gwa=gwa;
		this.dateHired=dateHired;
		this.address=address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getEmployed() {
		return employed;
	}

	public void setEmployed(Boolean employed) {
		this.employed = employed;
	}

	public float getGwa() {
		return gwa;
	}

	public void setGwa(float gwa) {
		this.gwa = gwa;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
