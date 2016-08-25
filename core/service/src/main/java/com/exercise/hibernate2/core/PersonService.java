package com.exercise.hibernate2.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class PersonService {

	private PersonDao personDao = new PersonDao();
	private ContactsDao contactsDao = new ContactsDao();
	private Validation check = new Validation();


	//option 1 done
	public Person getPerson(String personId) {
		return personDao.getPerson(personId);
	}

	public Address getPersonAddress(String personId){
		return personDao.getPersonAddress(personId);
	}

	//option2 done
	public void addPerson(Person person){
		personDao.addPerson(person);
	}

	//option3 done
	public void deletePerson(String personId){
		personDao.deletePerson(personId);
	}


	//option4
	public void updatePerson(String personId, Person updatedPerson){
		personDao.updatePerson(personId, updatedPerson);
	}

	//option 5 GWA
	public List<Person> getPersonsGwa(){
		List <Person> persons = personDao.getPersons("id");
		persons.sort((Person o1, Person o2)-> (int) ((o1.getGwa()*1000) - (o2.getGwa()*1000)));  //sort using lambda
		return persons;
	}

	//option 5 datehired and last name. Also use for display person by id
	public List<Person> getPersons(String order){
		List<Person> persons = personDao.getPersons(order);
		return persons;
	}

	public boolean checkPersonIfExist(String personId){
		if(personDao.getPerson(personId)== null){
			return false;
		}
		else{
			return true;
		}
	}

	public String checkInputPerson(String message){
		String personId= null;
		do{
			personId = check.inputIdPerson(message);
			if(checkPersonIfExist(personId)==false){
				System.out.print("Id number not exist! ");
			}
		}while((checkPersonIfExist(personId)) == false);
		return personId;
	}

}
