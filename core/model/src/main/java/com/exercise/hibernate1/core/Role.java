package com.exercise.hibernate1.core;

import javax.persistence.*;


@Entity
@Table(name = "Role")
public class Role extends PersistentObject{

	private String roleName;

	public Role(){}

	public Role(String roleName){
		this.roleName = roleName;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		Role obj2 = (Role)obj;
		if((this.roleName.equals(obj2.getRoleName())))
		{
			return true;
		}
		return false;
		}

	public int hashCode() {
		int tmp = 0;
		tmp = ( id + roleName ).hashCode();
		return tmp;
	}

}
