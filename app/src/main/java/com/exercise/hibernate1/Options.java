package com.exercise.hibernate1;

import java.util.*;
import com.exercise.hibernate1.core.*;

public class Options{

	private Scanner read = new Scanner(System.in);
	private Validation check = new Validation();
	private PersonService personService = new PersonService();
	private Person person = new Person();
	private static int zipCodeLength=4;
	private static int landlineLength=7;
	private static int mobileLength=11;

	//option1
	public void optionAddPerson(){
		displayAllPersons();
        Person person = addPersonInfo();
		List<Contacts> contacts = addContactsInfo();
		personService.addPerson(person,contacts);
    	System.out.println("\n\t  ============== UPDATED LIST ==============");
		displayAllPersons();
    	System.out.println("Person successfully added to database");
  	}

    //option2
  	public void optionDeletePerson(){
    	boolean emptyList = displayAllPersons();   // check if person list is empty. no person to delete
		if(emptyList==false){
			long personId = check.checkInputPerson("ID number of the person you want to delete.");
			personService.deletePerson(personId);
		  	System.out.println("\n\t  ============== UPDATED LIST ==============");
			displayAllPersons();
			System.out.println("Person successfully deleted from the database");
		}
	}

	//option3
	public void optionUpdatePerson(){
    	boolean emptyList = displayAllPersons();   // check if persons list is empty . no person to update
		if(emptyList==false){
	    	long personId = check.checkInputPerson("ID number of the person you want to update ");
	    	boolean continueUpdate = true;
			displayPersonInformation(personId);
	    	Person updatedPerson = personService.getPersonById(personId);
	    	Date dateHired = updatedPerson.getDateHired();
			displayUpdateMenu();
			int minChoice = 1;
			int maxChoice = 10;
	    	while(continueUpdate==true){
			int updateInfo = check.inputNumber(" corresponding number of the info you want to update.", minChoice, maxChoice);
		    	switch(updateInfo){
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
						boolean employed = check.inputYesOrNo("Currently Employed?");
						updatedPerson.setEmployed(employed);
						if(updatedPerson.getEmployed()==false){
							dateHired = null;
							updatedPerson.setDateHired(dateHired);
						}
						else{
							System.out.println("Employment Status now set to yes. Upd. ate hired date");
							dateHired = check.inputDate("date hired");
							updatedPerson.setDateHired(dateHired);
						}
						break;
					case 8:
						float gwa = check.inputGwa();
						updatedPerson.setGwa(gwa);
						break;
					case 9:
						if(updatedPerson.getEmployed()==true){
							dateHired = check.inputDate("date hired");
							updatedPerson.setDateHired(dateHired);
						}
						else{
							System.out.println("Person not employed. Can't update date hired. If currently employed update employement status first");
						}
						break;
					case 10:
						Address address = addAddressInfo();
						updatedPerson.setAddress(address);
						break;
						default:
						break;
				}
				continueUpdate = check.inputYesOrNo("Update another info to this person ? ");
			}
			System.out.println("UPDATED PERSON'S INFORMATION ");
			personService.updatePerson(personId, updatedPerson);
			displayPersonInformation(personId);
		}
	}

	//option4
	public void optionListPerson(){
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
		displayPersons(persons);
	}

	//option5
	public void optionAddContact(){
		displayAllPersons();
		long personId = check.checkInputPerson("ID number of the person you want to add contacts.");
		System.out.println();
		displayContactsInformation(personId);
		Contacts contacts = new Contacts();
		String contactType=null;
 		String contactValue=null;
 		boolean continueAddContacts= true;
 		while(continueAddContacts==true){
			int typeCon = check.inputNumber(" Contact Type: [1] Landline [2] Mobile [3] Email ", 1 , 3 );
			switch(typeCon){
				case 1:
					contactType = "Landline";
					contactValue = check.inputContactNumber("landline", landlineLength);
					break;
				case 2:
					contactType = "Mobile";
					contactValue = check.inputContactNumber("mobile", mobileLength);
					break;
				case 3:
					contactType = "Email";
					contactValue = check.inputEmail();
					break;
				default:
					break;
			}
			personService.addContact(personId, contactType,contactValue);
			continueAddContacts = check.inputYesOrNo("Want to add another contact?");
		}
		System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
		displayContactsInformation(personId);
		System.out.println("Contact/s succesfully added");
	}

	//option6
	public void optionUpdateContact(){
		displayAllPersons();
		long personId = check.checkInputPerson("ID number of the person you want to update contacts.");
		System.out.println();
		if(displayContactsInformation(personId)==true){
			long contactId = check.checkInputContact("contactId to update", personId);
			String newContactType= null;
			String newContactValue=null;
			List<Contacts> updatedContacts = personService.getPersonContactsById(personId);
			for(Contacts con : updatedContacts){
				if(con.getContactId()==contactId){
					newContactType = con.getContactType();
					break;
				}
			}
			switch(newContactType){
				case "Landline":
					newContactValue = check.inputContactNumber("landline", landlineLength);
					break;
				case "Mobile":
					newContactValue = check.inputContactNumber("mobile", mobileLength);
					break;
				case "Email":
					newContactValue = check.inputEmail();
					break;
				default:
					break;
			}
			personService.updateContact(contactId, newContactValue);
			System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
			displayContactsInformation(personId);
			System.out.println("Contact/s succesfully updated");
		}
	}

	//option7
	public void optionDeleteContact(){
		displayAllPersons();
		long personId = check.checkInputPerson("ID number of the person you want to delete contacts ");
		List<Contacts> updatedContacts = personService.getPersonContactsById(personId);
		System.out.println();
		if(displayContactsInformation(personId)==true){
			long contactId = check.checkInputContact("contactId to delete", personId);
			personService.deleteContact(contactId);
			System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
			displayContactsInformation(personId);
			System.out.println("Contact/s succesfully deleted");
		}
	}

	public Person addPersonInfo(){
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

  	public List<Contacts> addContactsInfo(){
		System.out.println("\n=========== Input Contacts ===========");
		List<Contacts> contacts = new ArrayList<Contacts>();
 		String contactType=null;
 		String contactValue=null;
 		boolean continueAddContacts= true;
 		while(continueAddContacts==true){
			int typeCon = check.inputNumber(" Contact Type: [1] Landline [2] Mobile [3] Email ", 1 , 3 );
			switch(typeCon){
				case 1:
					contactType = "Landline";
					contactValue = check.inputContactNumber("landline", landlineLength);
					break;
				case 2:
					contactType = "Mobile";
					contactValue = check.inputContactNumber("mobile", mobileLength);
					break;
				case 3:
					contactType = "Email";
					contactValue = check.inputEmail();
					break;
				default:
					break;
			}
			Contacts addCon = new Contacts(contactType,contactValue);
			contacts.add(addCon);
			continueAddContacts = check.inputYesOrNo("Want to add another contact?");
		}
		return contacts;
	}

	public void displayPersonInformation(long personId){
		Person person = personService.getPersonById(personId);
		System.out.println("\n\t  PERSON's ID: "+personId);
		System.out.println("\t  ============ PERSON'S INFORMATION ============");
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
		System.out.println("\t  |                                            |");
		displayAddressInformation(personId);
		displayContactsInformation(personId);
	}

	public void displayAddressInformation(long personId){
		Address address = personService.getPersonAddressById(personId);
		System.out.println("\t  =============== PERSON'S ADDRESS =============");
		System.out.println("\t  |                                            |");
		System.out.printf("\t  |  %-18s : %-20s %-1s\n","House Number",address.getHouseNo(),"|");
		System.out.printf("\t  |  %-18s : %-20s %-1s\n","Street",address.getStreet(),"|");
		System.out.printf("\t  |  %-18s : %-20s %-1s\n","Barangay",address.getBarangay(),"|");
		System.out.printf("\t  |  %-18s : %-20s %-1s\n","City",address.getCity(),"|");
		System.out.printf("\t  |  %-18s : %-20s %-1s\n","Zip Code",address.getZipCode(),"|");
		System.out.println("\t  |                                            |");
	}

	public boolean displayContactsInformation(long personId){
		List<Contacts> contacts = personService.getPersonContactsById(personId);
		boolean hasContacts = false;
		if(contacts.size()==0){
			System.out.println("\n\t  ==========================================");
			System.out.println("\t  |     No contacts for this person        |");
			System.out.println("\t  ==========================================\n");
			hasContacts = false;
		}
		else{
			System.out.println("\t  ============== PERSON'S CONTACTS =============");
			System.out.printf("\t  |  %-12s %-8s    %-12s     |\n","Contact ID","Type","Value");
			System.out.println("\t  ----------------------------------------------");
			for(Contacts c : contacts){
				System.out.printf("\t  |     %-7s %-12s %-17s %-1s\n",c.getContactId(),c.getContactType(),c.getContactValue(),"|");
			}
			System.out.println("\t  ==============================================\n");
			hasContacts = true;
		}
		return hasContacts;
	}

	public void displayPersons(List<Person> persons){
		System.out.println("\n   ==============================================================================================================================================");
		System.out.printf("   | %-3s|  %-12s|  %-12s|  %-12s|  %-8s|  %-7s|  %-15s|  %-11s|  %-6s|  %-12s| %-12s|\n","ID","First Name","Middle Name","Last Name","Suffix","Title","Date of Birth","Employed?","GWA","Date Hired","Address Id");
		System.out.println("   ----------------------------------------------------------------------------------------------------------------------------------------------");
		for (Person p : persons){
			System.out.printf("   | %-3s|  %-12s|  %-12s|  %-12s|  %-8s|  %-7s|  %-15s|  %-11s|  %-6s|  %-12s|    %-9s|\n",p.getPersonId(),p.getFirstName(),p.getMiddleName(),p.getLastName(),p.getSuffix(),p.getTitle(),p.getBirthDate(),p.getEmployed() == true ? "Yes":"No",p.getGwa(),p.getDateHired(),p.getAddress().getAddressId());
		}
		System.out.println("   ==============================================================================================================================================");
	}

	public boolean displayAllPersons(){
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

	public void menu(){
		displayAllPersons();
		System.out.println("\n\t  =============== MAIN MENU ================");
		System.out.println("\t  | Choose from the following options      |");
		System.out.println("\t  |    1  - Create Person                  |");
		System.out.println("\t  |    2  - Delete Person                  |");
		System.out.println("\t  |    3  - Update Person                  |");
		System.out.println("\t  |    4  - List Person                    |");
		System.out.println("\t  |    5  - Add Contact                    |");
		System.out.println("\t  |    6  - Update Contact                 |");
		System.out.println("\t  |    7  - Delete Contact                 |");
		System.out.println("\t  |    8  - Exit                           |");
		System.out.println("\t  ==========================================\n");
	}

	public void displayUpdateMenu(){
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

}
