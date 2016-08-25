package com.exercise.hibernate2;

import com.exercise.hibernate2.core.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class Options {

    private Scanner read = new Scanner(System.in);
    private Validation check = new Validation();
	private PersonService personService = new PersonService();
    private ContactsService contactsService = new ContactsService();
    private RoleService roleService = new RoleService();
    private static int landlineLength = 7;
    private static int mobileLength = 11;

    /*----------------------------- PERSON MENU------------------*/
    //option 1 done
    public void viewPerson(){
        if(personService.getPersons("personId").size()!=0) {
            displayPersons();
            String personId = personService.checkInputPerson("personId you want to view");
            if (personService.checkPersonIfExist(personId) == true) {
                displayPersonInfo(personId);
                displayPersonAddress(personId);
            } else {
                System.out.println("Person with id number " + personId + " not exist.");
            }
        }
        else{
            System.out.print("\n\t  ==========================================");
            System.out.print("\n\t  |     No persons in the database         |");
            System.out.print("\n\t  ==========================================\n");
        }
    }

	//option 2  done
    public void addPerson(){
        displayPersons();
        Person person = addPersonInfo();
		personService.addPerson(person);
        System.out.println("Person successfully added");
        System.out.println("\n\t  ============== UPDATED LIST ==============");
        displayPersons();
    }

	//option3 done
    public void deletePerson(){
        if(personService.getPersons("personId").size()!=0) {
            displayPersons();
            String personId = personService.checkInputPerson("personId you want to delete");
            if(check.inputYesOrNo("Are you sure you want to delete this person?")==true){
                personService.deletePerson(personId);
                System.out.println("Person successfully deleted");
                System.out.println("\n\t  ============== UPDATED LIST ==============");
                displayPersons();
            }
        }
        else{
            System.out.println("No person in the database. Nothing to delete");
        }
	}

	//option 4
    public void updatePerson(){
        if(personService.getPersons("personId").size()!=0) {
            displayPersons();
            String personId = personService.checkInputPerson("personId you want to update");
            displayPersonInfo(personId);
            displayPersonAddress(personId);
            Person updatedPerson = personService.getPerson(personId);
            boolean continueUpdate = true;
            updateMenu();
            while(continueUpdate==true) {
                int updateChoice = check.inputNumber(" corresponding number of the info you want to update.", 1, 10);
                switch (updateChoice) {
                    case 1:
                        String firstName = check.inputString("first name");
                        updatedPerson.setFirstName(firstName);
                        break;
                    case 2:
                        String middleName = check.inputString("middle name");
                        updatedPerson.setMiddleName(middleName);
                        break;
                    case 3:
                        String lastName = check.inputString("last name");
                        updatedPerson.setLastName(lastName);
                        break;
                    case 4:
                        String suffix = check.inputString("suffix or type (na) ");
                        updatedPerson.setSuffix(suffix);
                        break;
                    case 5:
                        String title = check.inputString("title");
                        updatedPerson.setTitle(title);
                        break;
                    case 6:
                        Date birthDate = check.inputDate("date of birth");
                        updatedPerson.setBirthDate(birthDate);
                        break;
                    case 7:
                        Boolean employed = check.inputYesOrNo("Currently Employed?");
                        updatedPerson.setEmployed(employed);
                        break;
                    case 8:
                        float gwa = check.inputGwa();
                        updatedPerson.setGwa(gwa);
                        break;
                    case 9:
                        Date dateHired = check.inputDate("date hired");
                        updatedPerson.setDateHired(dateHired);
                        break;
                    default:
                        Address address = addAddressInfo();
                        updatedPerson.setAddress(address);
                        break;
                }
                continueUpdate = check.inputYesOrNo("Update another info to this person?");
            }
            personService.updatePerson(personId, updatedPerson);
        }
        else{
            System.out.println("No person in the database. Nothing to delete");
        }
    }

	//option 5  done
	public void listPerson(){
		int minChoice = 1;
		int maxChoice = 3;
		List<Person> persons = null;
		String order = null;
		int listChoice = check.inputNumber(" type of order [ 1-by GWA ] [ 2 by Date Hired ] [ 3 by Last Name ] . " ,minChoice , maxChoice);
		switch(listChoice){
			case 1:
				persons = personService.getPersonsGwa();
				System.out.println("\n   LIST PERSON BY GWA");
				break;
			case 2:
				order = "dateHired";
				persons = personService.getPersons(order);
				System.out.println("\n   LIST PERSONS ORDER BY DATE HIRED");
				break;
			default:
				order = "lastName";
				persons = personService.getPersons(order);
				System.out.println("\n   LIST PERSONS ORDER BY LAST NAME");
				break;
        }
		displayPersonsList(persons);
	}

	/*-------------------- display all person --------------------- */

	public void displayPersons(){
		List<Person> personList = personService.getPersons("personId");
		if(personList.size()==0){
            System.out.print("\n\t  ==========================================");
            System.out.print("\n\t  |     No persons in the database         |");
            System.out.print("\n\t  ==========================================\n");
		}
		else{
			System.out.println("\n\t  =============== LIST OF PERSONS ============");
			System.out.println("\t  |  Person ID   First Name   Last Name      |");
			System.out.println("\t  -------------------------------------------");
			for(Person person : personList){
				System.out.printf ("\t  |  %-15s%-12s%-13s%-1s\n",person.getId(),person.getFirstName(),person.getLastName(),"|");
			}
			System.out.println("\t  ============================================\n");
		}
	}

	public void displayPersonsList(List<Person> persons){
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
		System.out.println("\n\t====================================================================================================================================================");
		System.out.printf("\t| %-12s|  %-11s|  %-12s|  %-11s|  %-8s|  %-7s|  %-15s|  %-11s|  %-6s|  %-12s| %-11s|\n","ID","First Name","Middle Name","Last Name","Suffix","Title","Date of Birth","Employed?","GWA","Date Hired","Address Id");
		System.out.println("\t----------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Person p : persons){
			System.out.printf("\t| %-12s|  %-11s|  %-12s|  %-11s|  %-8s|  %-7s|  %-15s|  %-11s|  %-6s|  %-12s|    %-8s|\n",p.getId(),p.getFirstName(),p.getMiddleName(),p.getLastName(),p.getSuffix(),p.getTitle(),formatter.format(p.getBirthDate()),p.getEmployed() == true ? "Yes":"No",p.getGwa(),formatter.format(p.getDateHired()),p.getAddress().getId());
		}
		System.out.println("\t===================================================================================================================================================");
	}


	/*----------------------  display person's info -----------------*/

  public void displayPersonInfo(String personId){
    Person person = personService.getPerson(personId);
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
    System.out.println("\n\t  ============ PERSON'S INFORMATION ============");
    System.out.println("\t  |                                            |");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","First Name",person.getFirstName(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Middle Name",person.getMiddleName(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Last Name",person.getLastName(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Suffix",person.getSuffix(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Title",person.getTitle(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Date of Birth",formatter.format(person.getBirthDate()),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Employment Status",person.getEmployed()==true ? "Employed" : "Not Employed","|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","GWA",person.getGwa(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Date Hired",formatter.format(person.getDateHired()),"|");
    System.out.println("\t  ==============================================");
  }

  public void displayPersonAddress(String personId){
    Address address = personService.getPersonAddress(personId);
    System.out.println("\n\t  =============== PERSON'S ADDRESS =============");
    System.out.println("\t  |                                            |");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","House Number",address.getHouseNo(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Street",address.getStreet(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Barangay",address.getBarangay(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","City",address.getCity(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Zip Code",address.getZipCode(),"|");
    System.out.println("\t  ==============================================\n");
  }


	/* ---------------- getting person's data from user ---------*/

	public Person addPersonInfo(){
        System.out.println("\n\n======= Input Basic Information ======");
        String firstName = check.inputString("First Name");
        String middleName = check.inputString("Middle Name");
        String lastName = check.inputString("Last Name");
        String suffix =check.inputString("suffix or type (na) ");
        String title = check.inputString("Title");
        Date birthDate = check.inputDate("Date of Birth");
        Boolean employed = check.inputEmployed("Currently Employed?");
        float gwa = check.inputGwa();
        Date dateHired = check.inputDate("Date hired");
        Address address = addAddressInfo();

        Set<Contact> contacts = addContactsNewPerson();
        Set<Role> roles = addRolesNewPerson();

        Person person = new Person(firstName, middleName,lastName, suffix, title,
        birthDate,employed,gwa, dateHired, address,contacts, roles);
        return person;
	}


   public Address addAddressInfo(){
     System.out.println("\n\n======= Input Address Information ======");
     int houseNo = check.inputNumber("House number");
     String street = check.inputString("Street");
     String barangay = check.inputString("Barangay");
     String city = check.inputString("City");
     int zipCode = check.inputNumber("ZipCode", 4);

     Address address = new Address(houseNo,street,barangay,city,zipCode);
     return address;
   }

   /*---------------------- update menu ----------------------------------*/
   public void updateMenu(){
       System.out.println("\n\t  ====== CHOOSE INFO YOU WANT TO EDIT =====");
       System.out.println("\t  |    1  - First Name                    |");
       System.out.println("\t  |    2  - Middle Name                   |");
       System.out.println("\t  |    3  - Last Name                     |");
       System.out.println("\t  |    4  - Suffix                        |");
       System.out.println("\t  |    5  - Title                         |");
       System.out.println("\t  |    6  - Date of Birth                 |");
       System.out.println("\t  |    7  - Employment Status             |");
       System.out.println("\t  |    8  - GWA                           |");
       System.out.println("\t  |    9  - Date Hired                    |");
       System.out.println("\t  |    10 - Address                       |");
       System.out.println("\t  =========================================\n");
   }


    /*----------------- CONTACT MENU ------------------------ */

    // option 1 done
    public void viewContacts(){
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to view contacts");
            displayPersonContacts(personId);
        } else {
            System.out.println("No Existing Person in the Database. Add person first in person management");
        }
    }

    public void displayPersonContacts(String personId){
        List<Contact> contacts = contactsService.getContacts(personId);
        if(contacts.size()==0){
            System.out.println("\n\t  ==========================================");
            System.out.println("\t  |     No contacts for this person        |");
            System.out.println("\t  ==========================================\n");
        }
        else{
            System.out.println("\n\t  ============== PERSON'S CONTACTS =============");
            System.out.printf("\t  |  %-12s %-8s    %-12s     |\n","Contact ID","Type","Value");
            System.out.println("\t  ----------------------------------------------");
            for(Contact c : contacts){
                System.out.printf("\t  |     %-7s %-11s %-19s%-1s\n",c.getId(),c.getContactType(),c.getContactValue(),"|");
            }
            System.out.println("\t  ==============================================\n");
        }
    }

    //option 2 done
    public void addContact(){
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to view contacts");
            displayPersonContacts(personId);
            int type = check.inputNumber(" type of contact 1-LANDLINE 2-MOBILE 3-EMAIL. ", 1, 3);
            Contact newContact = createContact(type);
            if(check.inputYesOrNo("Save this contact to this person?")==true) {
                contactsService.addContact(personId, newContact);
                System.out.println("Contact/s succesfully added");
                System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
                displayPersonContacts(personId);
            }
        }
        else {
            System.out.println("No Existing Person in the Database. Add person first in person management");
        }
    }

    public Contact createContact(int type){
        ContactType contactType = null;
        String contactValue= null;
        switch(type){
            case 1:
                contactType=ContactType.LANDLINE;
                contactValue=check.inputContactNumber("landline ", 7);
                break;
            case 2:
                contactType=ContactType.MOBILE;
                contactValue=check.inputContactNumber("mobile", 11);
                break;
            default:
                contactType=ContactType.EMAIL;
                contactValue=check.inputEmail();
                break;
        }
        Contact contact = new Contact(contactType,contactValue);
        return contact;
    }

    //option 3 done
    public void deleteContact() {
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to delete contacts");
            if (contactsService.getContacts(personId).size() != 0) {
                displayPersonContacts(personId);
                long contactId = contactsService.checkInputContact("contact Id to delete ", personId);
                if(check.inputYesOrNo("Are you sure you want to delete contact?")==true) {
                    contactsService.deleteContact(contactId);
                    System.out.println("Contact/s succesfully deleted");
                    System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
                    displayPersonContacts(personId);
                }
            } else {
                System.out.println("No contacts from this person. Nothing to delete contacts for this person");
            }
        } else {
            System.out.println("No Existing Person in the Database");
        }
    }

    //opton 4 done
    public void updateContact() {
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to update contacts");
            if (contactsService.getContacts(personId).size() != 0) {
                displayPersonContacts(personId);
                long contactId = contactsService.checkInputContact("contact Id to delete ", personId);
                Contact updatedContact = new Contact();
                updatedContact = contactsService.getContact(contactId);
                ContactType contactType = updatedContact.getContactType();
                String newContactValue = null;
                switch(contactType){
                    case LANDLINE:
                        newContactValue = check.inputContactNumber("landline", landlineLength);
                        break;
                    case MOBILE:
                        newContactValue = check.inputContactNumber("mobile", mobileLength);
                        break;
                    default:
                        newContactValue = check.inputEmail();
                        break;
                }
                if(check.inputYesOrNo("Are you sure you want to update this contact?")==true) {
                    contactsService.updateContact(contactId, newContactValue);
                    System.out.println("Contact/s succesfully updated");
                    System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
                    displayPersonContacts(personId);
                }
            } else {
                System.out.println("No contacts from this person. Nothing to update for this person");
            }
        } else {
            System.out.println("No Existing Person in the Database.");
        }
    }

    public Set<Contact> addContactsNewPerson(){
        Set<Contact> contacts = new HashSet<Contact>();
        boolean continueAdd = true;
        while(continueAdd==true){
            System.out.println("\n\n======= Input Contact Information ======");
            int type =check.inputNumber("choose type of contact 1-LANDLINE 2-MOBILE 3-EMAIL. ",1 ,3);
            Contact contact = createContact(type);
            contacts.add(contact);
            continueAdd = check.inputYesOrNo(" continue add Contact? (y/n) : ");
        }
        return contacts;
    }


    /*------------------------- ROLE MENU ---------------------------- */

    public void displayPersonRoles(String personId) {
        Set<Role>  personRoles= roleService.getPersonRoles(personId);
        if (personRoles.size() != 0) {
            System.out.println("\n\t  =============== PERSON'S ROLES ===============");
            for (Role r : personRoles) {
                System.out.printf("\t  |       %-5s :    %-23s %-1s\n", r.getId(), r.getRoleName(), "  |");
            }
              System.out.println("\t  ==============================================");
        } else {
            System.out.println("No roles for this person");
        }
    }

    //option 1 done
    public void viewPersonRoles() {
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to view roles");
            displayPersonRoles(personId);
        } else {
            System.out.println("No Existing Person in the Database. Add person first in person management");
        }
    }

    //option 2 done
    public void addPersonRole() {
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to add role");
            if (roleService.getRoles().size() != 0) {
                viewRoles();
                long roleId = roleService.checkInputRole("role id to add");
                Role role = roleService.getRole(roleId);
                roleService.addPersonRole(personId, role);
                System.out.println("Role successfully added to person");
            } else {
                System.out.println("No Existing Role in the Database. Add Role in the Database first.");
            }
        } else {
            System.out.println("No Existing Person in the Database. Add person in the database first");
        }
    }

    //option 3
    public void deletePersonRole() {
        if (personService.getPersons("personId").size() != 0) {
            displayPersons();
            String personId = personService.checkInputPerson("id number of the person you want to delete role");
            if (roleService.getPersonRoles(personId).size() != 0) {
                displayPersonRoles(personId);
                long roleId = roleService.checkInputRolePerson("role id in the person", personId);
                roleService.deletePersonRole(personId, roleId);
                System.out.println("Role successfully deleted to person");
            } else {
                System.out.println("No Existing Role for this person.");
            }
        } else {
            System.out.println("No Existing Person in the Database.");
        }
    }

    //option 4
    public void viewRoles() {
        List<Role> roles = roleService.getRoles();
        if (roles.size() != 0) {
            System.out.println("\n\t  ============= List of Roles ==============");
            for (Role r : roles) {
                System.out.printf("\t  |      %-5s :    %-22s %-1s\n", r.getId(), r.getRoleName(), "|");
            }
            System.out.println("\t  ==========================================");
        } else {
            System.out.print("\n\t  =============== List of Roles ============");
            System.out.print("\n\t  |            NO EXISTING ROLES           |");
            System.out.println("\n\t  ==========================================\n");
        }
    }

    //option 5
    public void addRole() {
        viewRoles();
        String newRoleName = null;
        boolean continueAdd = true;
        while (continueAdd == true) {
            newRoleName = check.inputString("new role name to add");
            List<Role> roles = roleService.getRoles();
            for (Role r : roles) {
                if (r.getRoleName() == newRoleName) {
                    System.out.println("Role already exist");
                } else {
                    break;
                }
            }
            roleService.addRole(newRoleName);
            continueAdd = check.inputYesOrNo("continue add role?");
        }
        System.out.println("\n\t  =========== UPDATED LIST OF ROLES =========\n");
        viewRoles();

    }

    //option 6
    public void updateRole() {
        List<Role> roles = roleService.getRoles();
        if (roles.size() != 0) {
            viewRoles();
            long roleId = roleService.checkInputRole("Enter roleId you want to delete");
            String updatedRole = check.inputString("input the updated role ");
            if (check.inputYesOrNo("Are you sure you want to replace role to this new one?") == true) {
                roleService.updateRole(roleId, updatedRole);
                System.out.println("\n\t  =========== UPDATED LIST OF ROLES =========\n");
                viewRoles();
            }
        } else {
            System.out.println("No roles in the database. Nothing to update");
        }
    }

    public Set<Role> addRolesNewPerson(){
        Set<Role> personRoles = new HashSet<Role>();
        Role role = null;
        viewRoles();
        boolean continueAdd = true;
        while(continueAdd==true) {
            System.out.println("\n\n======= Input Role Information ======");
            long roleId = roleService.checkInputRole("roleId you want to add to person. Choose from the above options");
            role = roleService.getRole(roleId);
            personRoles.add(role);
            continueAdd=check.inputYesOrNo("continue add role to this person?");
        }
        return personRoles;
    }

}
