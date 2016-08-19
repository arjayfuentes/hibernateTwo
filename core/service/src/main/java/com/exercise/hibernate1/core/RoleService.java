package com.exercise.hibernate1.core;

import java.util.*;

public class RoleService {

  Validation check = new Validation();
  RoleDao rDao = new RoleDao();

  //Role option1
  public Set<Role> getRoles(){
    return rDao.getRolesDatabase();
  }

  //Role option2
  public void addRole(String role){
    rDao.addRoleDatabase(role);
  }

  //Role options3
  public void deleteRole(long id){
    rDao.deleteRoleDatabase(id);
  }

  //Role option4
  public void updateRole(long id, String updatedRole){
    rDao.updateRoleDatabase(id, updatedRole);
  }

  public boolean checkRoleIfExist(long id){
    if(rDao.getRole(id)== null){
      return false;
    }
    else{
      return true;
    }
  }

  public Role getRole(roleId){
    return rDao.getRoleDatabase(roleId);
  }

}
