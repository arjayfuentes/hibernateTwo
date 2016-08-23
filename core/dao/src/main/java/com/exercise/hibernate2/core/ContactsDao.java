package com.exercise.hibernate2.core;

import java.util.*;
import org.hibernate.*;
import com.exercise.hibernate2.HibernateUtil;

public class ContactsDao {

	//option 1
	public List<Contact> getContacts(long personId){
		List <Contact> contacts = new ArrayList<Contact>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			String hql = "from Contact where personId = :id order by id";
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			query.setParameter("id",personId);
			contacts = query.list();
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
	public void addContact(long personId, Contact newContact){
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
	public void deleteContact(long contactId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		  tx = session.beginTransaction();
		  Contact contact = (Contact)session.get(Contact.class, contactId);
		  session.delete(contact);
		  tx.commit();
		}catch (HibernateException e) {
		  if (tx!=null) tx.rollback();
		  e.printStackTrace();
		}finally {
		  session.close();
		}
	}

  //contacts option 4
	public void updateContact(long contactId, String newContactValue){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
		  tx = session.beginTransaction();
		  Contact contact = (Contact)session.get(Contact.class, contactId);
		  contact.setContactValue(newContactValue);
		  session.update(contact);
		  tx.commit();
	    }catch (HibernateException e) {
		  if (tx!=null) tx.rollback();
		  e.printStackTrace();
		}finally {
		  session.close();
		}
	}

	public Contact getContact(long contactId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Contact contact = null;
		try{
			tx = session.beginTransaction();
			contact = (Contact)session.get(Contact.class, contactId);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return contact;
	}

}
