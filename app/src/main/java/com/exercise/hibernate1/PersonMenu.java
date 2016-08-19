package com.exercise.hibernate1;

import java.util.*;
import com.exercise.hibernate1.core.*;

public class PersonMenu{

	private Scanner read = new Scanner(System.in);
	private Validation check = new Validation();
	private PersonService personService = new PersonService();
	private Person person = new Person();
	private ContactsMenu contactsMenu = new ContactsMenu();
	private RoleMenu roleMenu = new RoleMenu();

  public void menu(){
    System.out.println("\n\t  =============== PERSON MENU ================");
    System.out.println("\t  | Choose from the following options      |");
    System.out.println("\t  |    1  - View Person                    |");
    System.out.println("\t  |    2  - Add Person                     |");
    System.out.println("\t  |    3  - Delete Person                  |");
    System.out.println("\t  |    4  - Update Person                  |");
    System.out.println("\t  |    5  - List Persons                   |");
    System.out.println("\t  ==========================================\n");
    int choice = check.inputNumber("person choice ", 1 , 5);
    switch(choice){
      case 1:
        view();
        break;
      case 2:
        add();
        break;
      case 3:
        delete();
        break;
      case 4:
        update();
        break;
      case 5:
        list();
        break;
      default:
        break;
    }
  }

	//option 1 done
  public void viewPerson(){
		displayPersons();
    long personId = check.checkInputPerson("ID number of the person you want to view: ");
    displayPersonInfo(personId);
  }

	//option 2  done
  public void addPerson(){
		Person person = new Person();
		person = addPersonInfo();
    Set <Contacts> contacts = contactsMenu.addContactsNewPerson();
	  Set <Role> roles = roleMenu.getRolesNewPerson();
		personService.addPerson(person,contaacts,roles);
  }

	//option 3 done
  public void deletePerson(){
		boolean emptyList = displayPersons();
		if(emptyList==false){
			long personId = check.checkInputPerson("ID number of the person you want to delete.");
			personService.deletePerson(personId);
			System.out.println("\n\t  ============== UPDATED LIST ==============");
			displayPersons();
		}
	}

	//option 4   OOOOOOOOOOOOOOO
  public void update(){
    long id = check.checkInputPerson("ID number of the person you want view: ")
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
				order = "date_hired";
				persons = personService.getPersons(order);
				System.out.println("\n   LIST PERSONS ORDER BY DATE HIRED");
				break;
			case 3:
				order = "last_name";
				persons = personService.getPersons(order);
				System.out.println("\n   LIST PERSONS ORDER BY LAST NAME");
				break;
			default:
				break;
		}
		displayPersonsList(persons);
	}

	/*-------------------- display all person --------------------- */

	public boolean displayPersons(){
		List<Person> personList = personService.getPersons("personId");
		if(personList.size()==0){
			System.out.println("\n\t  ==========================================");
			System.out.println("\t  |     No persons in the database         |");
			System.out.println("\t  ==========================================\n");
			return true;
		}
		else{
			System.out.println("\n\t  ============= LIST OF PERSONS ============");
			System.out.println("\t  |  Person ID   First Name   Last Name    |");
			System.out.println("\t  ------------------------------------------");
			for(Person person : personList){
				System.out.printf ("\t  |     %-10s%-12s%-13s%-1s\n",person.getPersonId(),person.getFirstName(),person.getLastName(),"|");
			}
			System.out.println("\t  ==========================================\n");
			return false;
		}
	}

	public void displayPersonsList(List<Person> persons){
		System.out.println("\n   ==============================================================================================================================================");
		System.out.printf("   | %-3s|  %-12s|  %-12s|  %-12s|  %-8s|  %-7s|  %-15s|  %-11s|  %-6s|  %-12s| %-12s|\n","ID","First Name","Middle Name","Last Name","Suffix","Title","Date of Birth","Employed?","GWA","Date Hired","Address Id");
		System.out.println("   ----------------------------------------------------------------------------------------------------------------------------------------------");
		for (Person p : persons){
			System.out.printf("   | %-3s|  %-12s|  %-12s|  %-12s|  %-8s|  %-7s|  %-15s|  %-11s|  %-6s|  %-12s|    %-9s|\n",p.getPersonId(),p.getFirstName(),p.getMiddleName(),p.getLastName(),p.getSuffix(),p.getTitle(),p.getBirthDate(),p.getEmployed() == true ? "Yes":"No",p.getGwa(),p.getDateHired(),p.getAddress().getAddressId());
		}
		System.out.println("   ==============================================================================================================================================");
	}


	/*----------------------  display person's info -----------------*/

  public void displayPersonInfo(long personId){
    Person person = personService.getPersonInfo(personId);
    System.out.println("\n\t  ============ PERSON'S INFORMATION ============");
    System.out.println("\t  |                                            |");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","First Name",person.getFirstName(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Middle Name",person.getMiddleName(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Last Name",person.getLastName(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Suffix",person.getSuffix(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Title",person.getTitle(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Date of Birth",person.getBirthDate(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Employment Status",person.getEmployed()==true ? "Employed" : "Not Employed","|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","GWA",person.getGwa(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Date Hired",person.getDateHired(),"|");
    System.out.println("\t  ==============================================");
  }

  public void displayPersonAddress(long personId){
    Address address = personService.getPersonAddress(personId);
    System.out.println("\n\t  =============== PERSON'S ADDRESS =============");
    System.out.println("\t  |                                            |");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","House Number",address.getHouseNo(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Street",address.getStreet(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Barangay",address.getBarangay(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","City",address.getCity(),"|");
    System.out.printf("\t  |  %-18s : %-20s %-1s\n","Zip Code",address.getZipCode(),"|");
    System.out.println("\t  ==============================================");
  }


	/* ---------------- getting person's data from user ---------*/

   public void addPersonInfo(){
     System.out.println("\n\n======= Input Basic Information ======");
     String firstName = check.inputString("First Name");
     String middleName = check.inputString("Middle Name");
     String lastName = check.inputString("Last Name");
     String suffix =check.inputString("suffix or type (na) ");
     String title = check.inputString("Title");
     Date birthDate = check.inputDate("Date of Birth");
     boolean employed = check.inputYesOrNo("Currently Employed?");
     float gwa = check.inputGwa();
     Date dateHired=null;
     if(employed==true){
       dateHired = check.inputDate("Date hired");
     }
     else{
       dateHired=null;
     }
     Address address = addAddressInfo();

     Person person = new Person(firstName, middleName,lastName, suffix, title,
       birthDate,employed,gwa, dateHired, address);
         return person;
     }
   }

   public Address addAddressInfo(){
     System.out.println("\n\n======= Input Basic Information ======");
     int houseNo = check.inputNumber("House number");
     String street = check.inputString("Street");
     String barangay = check.inputString("Barangay");
     String city = check.inputString("City");
     int zipCode = check.inputNumber("ZipCode", 4);

     Address address = new Address(houseNo,street,barangay,city,zipCode);
     return address;
   }

  }
