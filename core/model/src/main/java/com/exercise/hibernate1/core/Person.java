package com.exercise.hibernate1.core;

import java.util.Set;
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Person")
public class Person extends PersistentObject{

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "title")
	private String title;

	@Column(name = "date of birth")
	private Date birthDate;

	@Column(name = "Currently Employed")
	private boolean employed;

	@Column(name = "gwa")
	private float gwa;

	@Column(name = "date hired")
	private Date dateHired;

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Address address;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="personId")
	private Set<Contacts> contacts;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="PersonRole", joinColumns=@JoinColumn(name="person_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> role;

	public Person(){}

	public Person(String firstName, String middleName,String lastName, String suffix, String title,
		   Date birthDate,boolean employed,float gwa, Date dateHired, Address address){

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

	public boolean getEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
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

	public Set<Contacts> getContacts(){
		return contacts;
	}

	public void setContacts(Set<Contacts> contacts){
		this.contacts = contacts;
	}

	public Set<Role> getRole(){
		return role;
	}

	public void setRole(Set<Role> role){
		this.role = role;
	}

}
