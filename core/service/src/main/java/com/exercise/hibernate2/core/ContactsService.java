package com.exercise.hibernate2.core;

import java.util.List;

public class ContactsService {

	private ContactsDao contactsDao = new ContactsDao();
	private Validation check = new Validation();

	//option1 contacts
  	public List<Contact> getContacts(long personId){
    	return contactsDao.getContacts(personId);
  	}

	public boolean checkContactIfExist(long personId, long contactId){
		List<Contact> contacts = contactsDao.getContacts(personId);
		boolean contactExist = false;
		loop: for(Contact con : contacts){
			if(con.getId()==contactId){
				contactExist=true;
				break loop;
			}
		}
		return contactExist;
	}

	//option2 contacts
  	public void addContact(long personId, Contact newContact){
    	contactsDao.addContact(personId,newContact);
  	}

	//option3 contacts
	public void deleteContact(long contactId){
    	contactsDao.deleteContact(contactId);
  	}

	//option4 contacts
	public void updateContact(long contactId, String newContactValue){
    	contactsDao.updateContact(contactId, newContactValue);
	}

	public Contact getContact(long contactId){
		return contactsDao.getContact(contactId);
	}

	public long checkInputContact(String message, long personId){
		long contactId= 0;
		do{
			contactId = check.inputIdNumber(message);
			if(checkContactIfExist(personId, contactId)==false){
				System.out.print("contactId for person with personId = "+personId+" does not exist. ");
			}
		}while((checkContactIfExist(personId, contactId)) == false);
		return contactId;
	}


}
