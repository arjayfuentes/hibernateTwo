package com.exercise.hibernate2.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class PersonService {

	private PersonDao personDao = new PersonDao();
	private ContactsDao contactsDao = new ContactsDao();
	private Validation check = new Validation();


	//option 1 done
	public Person getPerson(long personId) {
		return personDao.getPerson(personId);
	}

	public Address getPersonAddress(long personId){
		return personDao.getPersonAddress(personId);
	}

	//option2 done
	public void addPerson(Person person, Set<Contact> contacts , Set<Role> roles){
		person.setContacts(contacts);
		person.setRoles(roles);
		personDao.addPerson(person);
	}

	//option3 done
	public void deletePerson(long personId){
		personDao.deletePerson(personId);
	}


	//option4
	public void updatePerson(long personId, Person updatedPerson){
		personDao.updatePerson(personId, updatedPerson);
	}

	//option 5 GWA
	public List<Person> getPersonsGwa(){
		List <Person> persons = personDao.getPersons();
		persons.sort((Person o1, Person o2)-> (int) ((o1.getGwa()*1000) - (o2.getGwa()*1000)));  //sort using lambda
		return persons;
	}

	//option 5 datehired and last name. Also use for display person by id
	public List<Person> getPersons(String order){
		List<Person> persons = new ArrayList<Person>();
		if (order.equals("last_name")){
			persons = personDao.getPersons("last_name");
		} else if (order.equals("date_hired")){
			persons = personDao.getPersons("date_hired");
		} else {
			persons = personDao.getPersons("id");
		}
		return persons;
	}

	public boolean checkPersonIfExist(long personId){
		if(personDao.getPerson(personId)== null){
			return false;
		}
		else{
			return true;
		}
	}

	public long checkInputPerson(String message){
		long personId= 0;
		do{
			personId = check.inputIdNumber(message);
			if(checkPersonIfExist(personId)==false){
				System.out.print("Id number not exist! ");
			}
		}while((checkPersonIfExist(personId)) == false);
		return personId;
	}

}
