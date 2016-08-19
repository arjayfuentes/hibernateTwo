package com.exercise.hibernate1.core;

import java.util.List;
import java.util.ArrayList;

public class PersonService {

	private PersonDao personDao = new PersonDao();
	private ContactsDao contactsDao = new ContactsDao();

	//option 1 done
	public Person getPersonInfo(long personId) {
		return personDao.getPersonInfoDatabase(personId);
	}

	public Address getPersonAddress(long personId){
		return personDao.getPersonAddressDatabase(personId);
	}

	//option 2 done
	public void addPerson(Person person, Set<Contacts> contacts , Set<Role> roles){
		person.setContacts(contacts);
		person.setRole(roles);
		personDao.addPersonDatabase(person);
	}

	//option 3 done
	public void deletePerson(long personId){
		personDao.deletePersonDatabase(personId);
	}


	//option4  0000000000000000
	public void updatePerson(long personId, Person updatedPerson){
		personDao.updatePersonToDatabase(personId, updatedPerson);
	}

	//option 5 GWA
	public List<Person> getPersonsGwa(){
		List <Person> persons = personDao.getPersonsDatabase("personId");
		persons.sort((Person o1, Person o2)-> (int) ((o1.getGwa()*1000) - (o2.getGwa()*1000)));  //sort using lambda
		return persons;
	}

	//option 5 datehired and last name and use for display by id
	public List<Person> getPersons(String order){
		List<Person> persons = new ArrayList<Person>();
		if (order.equals("last_name")){
			persons = personDao.getPersonsDatabase("last_name");
		} else if (order.equals("date_hired")){
			persons = personDao.getPersonsDatabase("date_hired");
		} else {
			persons = personDao.getPersonsDatabase("personId");
		}
		return persons;
	}







}
