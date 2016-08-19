package com.exercise.hibernate1.core;

import java.util.Set;

public class ContactsService {

	private ContactsDao contactsDao = new ContactsDao();

  public Set<Contacts> getContacts(long id){
    return contactsDao.getContactsDatabase(id);
  }

  public void addContact(long id, Contacts newContact){
    contactsDao.addContactPersonDatabase(id,newContact);
  }

  public void updateContact(long id, String newContactValue){
    contactsDao.updateContactPersonDatabase(id, newContactValue);
  }

  public void deleteContact(long id){
    contactsDao.deleteContactPersonDatabase(id);
  }

}
