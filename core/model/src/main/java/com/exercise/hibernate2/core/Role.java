package com.exercise.hibernate2.core;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE ,region="Role")
@Entity
@Table(name = "Role")

public class Role extends  PersistentObject{

	@Column(name="roleName")
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
		//if((this.roleId == obj2.getRoleId()) && (this.roleName.equals(obj2.getRoleName())))
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
