package com.exercise.hibernate1.core;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import com.exercise.hibernate1.HibernateUtil;

public class ContactsDao {

	//option 1
	public Set<Contacts> getContactsPerson(long personId){
		Set<Contacts> contacts = new HashSet<Contacts>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
				tx = session.beginTransaction();
				Person person =(Person)session.get(Person.class, personId);
				contacts = person.getContacts();
				tx.commit();
		}catch (HibernateException e) {
		  if (tx!=null) tx.rollback();
		  e.printStackTrace();
		}finally {
		  session.close();
		}
		return contacts;
	}

	//contacts option 2
	public void addContactPersonDatabase(long personId, Contacts newContact){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		  tx = session.beginTransaction();
		  Person person =(Person)session.get(Person.class, personId);
		  person.getContacts().add(newContact);
		  session.update(person);
		  tx.commit();
		}catch (HibernateException e) {
		  if (tx!=null) tx.rollback();
		  e.printStackTrace();
		}finally {
		  session.close();
		}
	}

  //contacts option 3
	public void updateContactPersonDatabase(long id, String newContactValue){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		  tx = session.beginTransaction();
		  Contacts contacts = (Contacts)session.get(Contacts.class, id);
		  contacts.setContactValue(newContactValue);
		  session.update(contacts);
		  tx.commit();
	    }catch (HibernateException e) {
		  if (tx!=null) tx.rollback();
		  e.printStackTrace();
		}finally {
		  session.close();
		}
	}

  //contacts option 4
	public void deleteContactPersonDatabase(long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		  tx = session.beginTransaction();
		  Contacts contacts = (Contacts)session.get(Contacts.class, id);
		  session.delete(contacts);
		  tx.commit();
		}catch (HibernateException e) {
		  if (tx!=null) tx.rollback();
		  e.printStackTrace();
		}finally {
		  session.close();
		}
	}


}
