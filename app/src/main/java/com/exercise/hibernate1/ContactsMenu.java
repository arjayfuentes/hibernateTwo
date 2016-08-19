package com.exercise.hibernate1;

import java.util.*;
import com.exercise.hibernate1.core.*;

public class ContactsMenu{

	private Scanner read = new Scanner(System.in);
	private Validation check = new Validation();
	private ContactsService contactsService = new ContactsService();

  public void menu(){
		boolean continueProcess = true;
		while(continueProcess == true){
	    System.out.println("\n\t  =============== CONTACT MENU ================");
	    System.out.println("\t  | Choose from the following options      |");
	    System.out.println("\t  |    1  - View Person Contacts         	 |");
	    System.out.println("\t  |    2  - Add Contact                    |");
	    System.out.println("\t  |    3  - Delete Contact                 |");
	    System.out.println("\t  |    4  - Update Contact                 |");
			System.out.println("\t  |    5  - Back to Main Menu              |");
	    System.out.println("\t  ==========================================\n");
	    int choice = check.inputNumber("person choice ", 1 , 4);
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
	      default:
					continueProcess= false;
	        break;
	    }
		}
	}

	public void viewContacts(){
		long personId = check.checkInputPerson("ID number of the person you want view Contacts: "); // validatac
		displayContactsInformation(personId);
	}

	public void addContact(){
		//display persons;;
		long personId = check.checkInputPerson("ID number of the person you want add Contacts: ");
		boolean continueAddContact = true;
		while(continueAddContact= true){
			int type = check.inputNumber(" type of contact 1-LANDLINE 2-MOBILE 3-EMAIL. ", 1, 3);
			Contacts newContact = creatContact(type);
			contactsService.addContact(personId,newContact);
			continueAddContact = check.inputYesOrNo(" continue add Contacts? (y/n) : ");
		}
	}

	public void deleteContact(){
		// display persons list displayAllPersons();
		long personId = check.checkInputPerson("ID number of the person you want to delete contacts ");
		Set<Contacts> updatedContacts = contactsService.getContacts(personId);
		System.out.println();
		if(displayContactsInformation(personId)==true){
			long contactId = check.checkInputContact("contactId to delete", personId);
			personService.deleteContact(contactId);
			System.out.println("\n\t  ============= UPDATED CONTACTS ===========\n");
			displayContactsInformation(personId);
			System.out.println("Contact/s succesfully deleted");
		}
	}


	public boolean displayContactsInformation(long personId){
		Set<Contacts> contacts = contactsService.getContacts(personId);
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


	public void update(){
		long id = check.checkInputPerson("ID number of the person you want update Contacts: ");
	}


	public Set<Contacts> addContactsNewPerson(){
		Set<Contacts> contacts = new HashSet<Contacts>;
		boolean continueAddContacts = true;
		while(continueAddContacts==true){
			int type =check.inputNumber(" choose type of contact 1-LANDLINE 2-MOBILE 3-EMAIL. ",1 ,3);
			Contacts contact = createContacat(type);
			contacts.add(contact);
			continueAddContact = check.inputYesOrNo(" continue add Contacts? (y/n) : ");
		}
		return contacts;
	}

	public Contacts createContact(int type){
		ContactType contactType = null;
		String contactValue= null;
		switch(type){
			case 1:
				contactType=ContactType.LANDLINE;
				contactValue=check.inputContactNumber(contactType, 7);
			case 2:
				contactType=ContactType.MOBILE;
				contactValue=check.inputContactNumber(contactType, 11);
			default:
				contactType=ContactType.EMAIL;
				contactValue=check.inputEmail();
		}
		Contacts contact = new Contact(contactType,contactValue);
		return contact;
	}

}
