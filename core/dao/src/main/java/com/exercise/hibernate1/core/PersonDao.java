package com.exercise.hibernate1.core;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import com.exercise.hibernate1.HibernateUtil;

public class PersonDao {

	/* ----------------------- fetch data for display ------------------*/

	public List<Person> getPersonsDatabase(String order) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	  List persons = null;
	  try {
			tx = session.beginTransaction();
		 	Query query = null;
			if (order.equals("last_name")){
				query = session.createQuery("from Person p ORDER BY p.lastName ASC");
			} else if (order.equals("date_hired")){
				query = session.createQuery("from Person p ORDER BY p.dateHired ASC");
			} else {
				query = session.createQuery("from Person p ORDER BY p.personId ASC");
			}
		  persons = query.list();
			tx.commit();
		}catch (HibernateException e) {
	    if (tx!=null) tx.rollback();
	    e.printStackTrace();
		}finally {
	    session.close();
		}
		return persons;
	}

	public Person getPersonInfoDatabase(long personId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person= null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			person = (Person) session.get(Person.class, personId);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return person;
	}

	public Address getPersonAddressDatabase(long personId){
		Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
		Address address = new Address();
		try{
			tx = session.beginTransaction();
			Person person =(Person)session.get(Person.class, personId);
      address = person.getAddress();
			long addressId = address.getId();
			String hql = "from Address where addressId = :id";
      Query query = session.createQuery(hql);
			query.setParameter("id",addressId);
			List <Address> addressList = new ArrayList <Address>();
			addressList = query.list();
			address = addressList.get(0);
      tx.commit();
    }catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    }finally {
      session.close();
    }
		return address;
	}

	//option2 done
	public void addPersonDatabase(Person person){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(person);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	//option3 done
	public void deletePersonDatabase(long personId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
				tx = session.beginTransaction();
				Person person = (Person) session.get(Person.class, personId);
				session.delete(person);
				tx.commit();
		}catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace();
		}finally {
				session.close();
		}
	}







































    //option3
    public void updatePersonToDatabase(long personId, Person updatedPerson){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
		Address address = null;
        try{
		tx = session.beginTransaction();
		Person person =(Person)session.get(Person.class, personId);
		person.setFirstName(updatedPerson.getFirstName());
		person.setMiddleName(updatedPerson.getMiddleName());
		person.setLastName(updatedPerson.getLastName());
		person.setSuffix(updatedPerson.getSuffix());
		person.setTitle(updatedPerson.getTitle());
		person.setBirthDate(updatedPerson.getBirthDate());
		person.setEmployed(updatedPerson.getEmployed());
		person.setGwa(updatedPerson.getGwa());
		person.setDateHired(updatedPerson.getDateHired());
		person.setAddress(updatedPerson.getAddress());
		session.update(person);
		tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    //option 4 GWA
	public List<Person> getAllPersonsFromDatabase(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Person> persons = new ArrayList<>();
		try{
		    tx = session.beginTransaction();
		    persons = session.createQuery("from Person").list();
		    tx.commit();
		}catch (HibernateException e) {
		    if (tx!=null) tx.rollback();
		    e.printStackTrace();
		}finally {
		    session.close();
		}
		return persons;
	}

	//options 4 date hired and last name


    /*--------------------------------- fetching data --------------------------------------*/

	//use to check if person exist and updating person's info
	public Person getPersonById(long personId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person= null;
        	Transaction tx = null;
		try{
			tx = session.beginTransaction();
			person = (Person) session.get(Person.class, personId);
			tx.commit();
		}catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
		return person;
	}

    	//displaying address




}
