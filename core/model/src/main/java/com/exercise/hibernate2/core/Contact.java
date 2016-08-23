package com.exercise.hibernate2.core;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import com.exercise.hibernate2.core.ContactType;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE ,region="Contact")
@Entity
@Table(name = "Contact")
public class Contact extends PersistentObject{


	@Column(name = "contactType")
	@Enumerated(EnumType.STRING)
	private ContactType contactType;

	@Column(name = "contactValue")
	private String contactValue;

	public Contact(){}

	public Contact(ContactType contactType, String contactValue){
		this.contactType = contactType;
		this.contactValue = contactValue;
	}


	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		com.exercise.hibernate2.core.Contact obj2 = (com.exercise.hibernate2.core.Contact)obj;
		//if((this.roleId == obj2.getRoleId()) && (this.roleName.equals(obj2.getRoleName())))
		if((this.contactValue.equals(obj2.getContactValue())))
		{
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
