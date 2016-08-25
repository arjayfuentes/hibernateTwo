package com.exercise.hibernate2.core;

import java.util.*;

import com.exercise.hibernate2.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PersonDao {

	public Person getPerson(String personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			person = (Person) session.get(Person.class,personId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;
	}

	public Address getPersonAddress(String personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Person person = null;
		Address address = new Address();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.add(Restrictions.eq("id",personId))
					.setCacheable(true);
			person= (Person) criteria.uniqueResult();
			address = person.getAddress();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return address;
	}

	public List<Person> getPersons(String order) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List <Person> persons = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.setCacheable(true);
			
			if (order.equals("lastName")) {
				criteria.addOrder(Order.asc("lastName"));
			} else if (order.equals("dateHired")) {
				criteria.addOrder(Order.asc("dateHired"));
			} else {
				criteria.addOrder(Order.asc("id"));
			}
			persons = (List <Person>)criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persons;
	}

	//option2 done
	public void addPerson(Person person) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	//option3 done
	public void deletePerson(String personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Person person = (Person) session.get(Person.class, personId);
			Set<Role> roles = person.getRoles();
			person.getRoles().removeAll(roles);
			session.delete(person);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	//option 4
	public void updatePerson(String personId, Person updatedPerson){
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

}
