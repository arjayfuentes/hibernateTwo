package com.exercise.hibernate1;

import java.util.*;
import com.exercise.hibernate1.core.*;

public class RoleMenu{

	private Validation check = new Validation();
	private RoleService roleService = new RoleService();

  public void roleMenu(){
		boolean continueProcess = true;
		while(continueProcess==true){
	    System.out.println("\n\t  =============== ROLE MENU ================");
	    System.out.println("\t  | Choose from the following Role Options |");
	    System.out.println("\t  |    1  - View Roles                   	 |");
	    System.out.println("\t  |    2  - Add Role                       |");
	    System.out.println("\t  |    3  - Delete Role                    |");
			System.out.println("\t  |    4  - Update Role                    |");
			System.out.println("\t  |    5  - Back to Main Menu              |");
	    System.out.println("\t  ==========================================\n");
	    int choice = check.inputNumber("person choice ", 1 , 5);
	    switch(choice){
	      case 1:
	        viewRoles();
	        break;
	      case 2:
	        addRole();
	        break;
	      case 3:
	        deleteRole();
	        break;
				case 4:
		      updateRole();
		      break;
	      default:
					continueProcess= false;
	        break;
	    }
		}
	}

	public void viewRoles(){
    Set<Role> roles = roleService.getRoles();
		System.out.println("\n\t  =============== List of Roles ===============");
		for(Role r : roles){
				System.out.printf("\t  |  %-5s : %-25s %-1s\n",r.getId(),r.getRoleName(),"|");
		}
		System.out.println("\n\t  =============================================");
	}

  public void addRole(){
    String role = check.inputString(" role you want to add");
    roleService.addRole(role);
  }

  public void deleteRole(){
		viewRoles();
		long id = check.checkInputRole(" role id number you want to delete. ");
		roleService.deleteRole(id);
  }

	public void updateRole(){
		viewRoles();
		long id = check.checkInputRole(" role id number you want to delete. ");
		String updateRole = check.inputString(" role to update");
		roleService.updateRole(id,updatedRole);
  }

	public Role getRole(roleId){
		return roleService.getRole(roleId);
	}

	public Set<Role> addRolesNewPerson(){
		
	}



}
