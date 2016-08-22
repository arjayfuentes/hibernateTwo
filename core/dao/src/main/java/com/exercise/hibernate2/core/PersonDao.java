package com.exercise.hibernate2.core;

import java.util.*;

import com.exercise.hibernate2.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PersonDao {

	public Person getPerson(long personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);  
    		criteria.add(Restrictions.eq("id",personId));
    		person = (Person) criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;
	}

	public Address getPersonAddress(long personId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Address address = new Address();
		try {
			tx = session.beginTransaction();
			Person person = (Person) session.get(Person.class, personId);
			address = person.getAddress();
			long addressId = address.getId();
			String hql = "from Address where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", addressId);
			List<Address> addressList = new ArrayList<Address>();
			addressList = query.list();
			address = addressList.get(0);
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

			if (order.equals("last_name")) {
				criteria.addOrder(Order.asc("last_name"));
			} else if (order.equals("date_hired")) {
				criteria.addOrder(Order.asc("date_hired"));
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

	public List<Person> getPersons(){
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
	public void deletePerson(long personId) {
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
	public void updatePerson(long personId, Person updatedPerson){
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
			address = updatedPerson.getAddress();
			person.setAddress(address);
			session.merge(person);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
