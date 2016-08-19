package com.exercise.hibernate1.core;

import javax.persistence.*;

@Entity
@Table(name = "Contacts")
public class Contacts extends PersistentObject{

	@Column(name = "contact_type")
	@Enumerated(EnumType.STRING)
	private ContactType contactType;

	@Column(name = "value")
	private String contactValue;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;


	public Contacts(){}

	public Contacts(ContactType contactType, String contactValue){
		this.contactType = contactType;
		this.contactValue = contactValue;
	}

	public ContactType getContactType(){
		return contactType;
	}

	public void setContactType(ContactType contactType){
		this.contactType = contactType;
	}

	public String getContactValue(){
		return contactValue;
	}

	public void setContactValue(String contactValue){
		this.contactValue = contactValue;
	}

	public Person getPerson(){
			return person;
	}

	public void setPerson(Person person){
			this.person = person;
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		Contacts obj2 = (Contacts)obj;
		if((this.contactValue.equals(obj2.getContactValue()))){
			return true;
		}
		return false;
	}

	public int hashCode() {
		int tmp = 0;
		tmp = ( id + contactValue ).hashCode();
		return tmp;
	}


}
