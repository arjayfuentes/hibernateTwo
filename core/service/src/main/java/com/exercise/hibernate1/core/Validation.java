package com.exercise.hibernate1.core;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class Validation {

	private Scanner read = new Scanner (System.in);
	private PersonService personService = new PersonService();

	public int inputNumber(String numberType){
		int number=0;
		System.out.printf("Enter %s: ",numberType);
		while (!read.hasNextInt()) {
			System.out.printf("Not a number! Enter %s: ",numberType);
			read.next();
		}
		number = read.nextInt();
		read.nextLine();
		return number;
	}

	public long inputIdNumber(String numberType){
		long number=0;
		System.out.printf("Enter %s: ",numberType);
		while (!read.hasNextLong()) {
			System.out.printf("Not a number! Enter %s: ",numberType);
			read.next();
		}
		number = read.nextLong();
		read.nextLine();
		return number;
	}

	public int inputNumber(String numberType, int min, int max){
		int number=0;
		do {
			System.out.print("Enter"+numberType+"("+min+"-"+max+"): ");
			while (!read.hasNextInt()) {
				System.out.printf("Not a number! Enter"+numberType+"("+min+"-"+max+"): ");
				read.next();
			}
			number = read.nextInt();
			read.nextLine();
			if(number<min || number> max){
				System.out.print("Invalid Input! ");
			}
		} while (number<min || number >max);
	  	return number;
	}

	public int inputNumber(String numberType,int length){
		int number=0;
		do {
			System.out.printf("Enter %s (%d digits): ",numberType,length);
			while (!read.hasNextInt()) {
			  	System.out.printf("Not a number! Enter %s (%d digits): ",numberType,length);
			  	read.next();
			}
			number = read.nextInt();
			read.nextLine();
			if(Integer.toString(number).length()!=length) {
				System.out.print("Invalid length of integers. ");
			}
		} while (Integer.toString(number).length()!=length);
		return number;
	}

	public String inputContactNumber(ContactType contactType,int length){
		long number=0;
		String numberFinal= null;
		do {
			System.out.printf("Enter "+contactType+" ("+length+" digits)");
			while (!read.hasNextLong()) {
			  	System.out.printf("Not a number!Re-enter value");
			  	read.next();
			}
			numberFinal = read.next();
			read.nextLine();
			if(numberFinal.length()!=length) {
				System.out.print("Invalid length of integers. ");
			}
		} while (numberFinal.length()!=length);
		return numberFinal;
	}

	public String inputString(String stringType){
		System.out.print("Enter "+stringType+": ");
		String string = read.nextLine();
		while (string.length()==0){
		   if(string.length()==0){
		   System.out.print("Empty input. Re-enter "+stringType+": ");
		   }
		   string = read.nextLine();
		}
		string=string.trim();
		string=StringUtils.capitalize(string);
		return string;
	}

	public String inputEmail(){
		EmailValidator emailValid = EmailValidator.getInstance();
		boolean validEmail =false;
		String email=null;
		while(validEmail==false){
			System.out.print("Enter email address: ");
			email=read.next();
			if(emailValid.isValid(email)){
				validEmail=true;
			}
			else{
				System.out.print("Invalid Email. ");
			}

		}
		return email;
	}

	public float inputGwa(){
		System.out.print("Enter GWA: ");
		int min = 1;
		int max = 5;
		float input=0;
		do {
			 while (!read.hasNextFloat()) {
			  	System.out.print("Not a float number!. Re-enter Gwa: ");
			  	read.next();
			 }
			 input = read.nextFloat();
			 read.nextLine();
			 if(input<min || input> max){
				 System.out.print("Invalid input GWA should be from 1-5 ! ");
			 }
		 } while (input<min || input >max);
		 DecimalFormat twoDForm = new DecimalFormat("#.##");
		 return (float) Float.valueOf(twoDForm.format(input));
	}

	public boolean inputYesOrNo(String askBoolean){
		System.out.print(askBoolean+" (Y/N) ");
		boolean ask = false;
		String input = read.next();
		boolean correct=false;
		while(correct==false){
			if(input.equalsIgnoreCase("Y")){
				correct= true;
				ask=true;
				break;
			}
			else if(input.equalsIgnoreCase("N")){
				correct= true;
				ask=false;
				break;
			}
			else{
			System.out.print("Invalid input. Only (Y/N) or (y/n) : ");
			}
			input = read.next();
		}
		return ask;
	}

	public Date inputDate(String dateType){
		System.out.print("Enter "+dateType+" (MM/DD/YYYY): ");
		String input = read.nextLine();
		while(!input.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
				System.out.print("Invalid date format. Re-enter date: ");
				input = read.next();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date checkDate=null;
		try {
			checkDate = formatter.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return checkDate;
	}

	public long checkInputPerson(String message){
    long personId= 0;
    do{
    		personId = inputIdNumber(message);
    		if(personService.checkPersonIfExist(personId)==false){
    			System.out.print("Id number not exist! ");
    		}
    	}while((personService.checkPersonIfExist(personId)) == false);
    	return personId;
    }

	public long checkInputContact(String message, long personId){
    	long contactId= 0;
    	do{
    		contactId = inputIdNumber(message);
    		if(personService.checkContactIfExist(personId, contactId)==false){
    			System.out.print("contactId for person with personId = "+personId+" does not exist. ");
    		}
    	}while((personService.checkContactIfExist(personId, contactId)) == false);
        return contactId;
  }

	public long checkInputRole(String message){
		long id = 0;
		do{
			id = inputIdNumber(message);
			if(roleService.checkRoleIfExist(id)==false){
				System.out.print(" Id number not exist! ");
			}
		}while(roleService.checkRoleIfExist(id)==false);
		return id;
	}









}
