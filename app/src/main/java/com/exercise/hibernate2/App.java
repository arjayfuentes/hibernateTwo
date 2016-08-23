package com.exercise.hibernate2;

import com.exercise.hibernate2.core.*;

public class App{

	private Options options = new Options();
	private Validation check = new Validation();

	public static void main(String [] args){
		App app = new App();
		app.mainMenu();
	}

	public void mainMenu(){
		boolean processContinue = true;
		while(processContinue==true){
			System.out.println("\n\t  =============== MAIN MENU ================");
			System.out.println("\t  | Choose from the following options      |");
			System.out.println("\t  |    1  - Person Management              |");
			System.out.println("\t  |    2  - Contact Management             |");
			System.out.println("\t  |    3  - Role Management                |");
			System.out.println("\t  |    4  - Exit                           |");
			System.out.println("\t  ==========================================\n");
				int choice=check.inputNumber("Choice: ");
				switch(choice){
					case 1:
						personMenu();
						break;
					case 2:
						contactMenu();
						break;
					case 3:
						roleMenu();
						break;
					default:
						processContinue=false;
						System.exit(0);
						break;
				}
		}
	}

	public void personMenu(){
		boolean continueProcess = true;
		while(continueProcess == true){
			System.out.println("\n\t  =============== PERSON MENU ==============");
			System.out.println("\t  | Choose from the following options      |");
			System.out.println("\t  |    1  - View Person                    |");
			System.out.println("\t  |    2  - Add Person                     |");
			System.out.println("\t  |    3  - Delete Person                  |");
			System.out.println("\t  |    4  - Update Person                  |");
			System.out.println("\t  |    5  - List Persons                   |");
			System.out.println("\t  |    6  - Back to Main Menu              |");
			System.out.println("\t  ==========================================\n");
			int choice = check.inputNumber("Person Choice ", 1 , 6);
			switch(choice) {
				case 1:
					options.viewPerson();
					break;
				case 2:
					options.addPerson();
					break;
				case 3:
					options.deletePerson();
					break;
				case 4:
					options.updatePerson();
					break;
				case 5:
					options.listPerson();
					break;
				default:
					continueProcess = false;
					break;
			}
		}
	}

	public void contactMenu(){
		boolean continueProcess = true;
		while(continueProcess == true){
			System.out.println("\n\t  =============== CONTACT MENU ==============");
			System.out.println("\t  | Choose from the following options      |");
			System.out.println("\t  |    1  - View Person Contact            |");
			System.out.println("\t  |    2  - Add Contact                    |");
			System.out.println("\t  |    3  - Delete Contact                 |");
			System.out.println("\t  |    4  - Update Contact                 |");
			System.out.println("\t  |    5  - Back to Main Menu              |");
			System.out.println("\t  ==========================================\n");
			int choice = check.inputNumber("Contact Choice ", 1 , 5);
			switch(choice){
				case 1:
					options.viewContacts();
					break;
				case 2:
					options.addContact();
					break;
				case 3:
					options.deleteContact();
					break;
				case 4:
					options.updateContact();
					break;
				default:
					continueProcess= false;
					break;
			}
		}
	}

	public void roleMenu() {
		boolean continueProcess = true;
		while (continueProcess == true) {
			System.out.println("\n\t  =============== ROLE MENU ================");
			System.out.println("\t  | Choose from the following Role Options |");
			System.out.println("\t  |    1  - View Roles of Person           |");
			System.out.println("\t  |    2  - Add Role to Person             |");
			System.out.println("\t  |    3  - Delete Role to Person          |");
			System.out.println("\t  |    4  - View List of Roles             |");
			System.out.println("\t  |    5  - Add Role to Database           |");
			System.out.println("\t  |    6  - Delete Role in Database        |");
			System.out.println("\t  |    7  - Update Role in Database        |");
			System.out.println("\t  |    8  - Back to Main menu              |");
			System.out.println("\t  ==========================================\n");
			int choice = check.inputNumber("Role Choice ", 1, 9);
			switch (choice) {
				case 1:
					options.viewPersonRoles();
					break;
				case 2:
					options.addPersonRole();
					break;
				case 3:
					options.deletePersonRole();
					break;
				case 4:
					options.viewRoles();
					break;
				case 5:
					options.addRole();
					break;
				case 6:
					options.deleteRole();
					break;
				case 7:
					options.updateRole();
					break;
				default:
					continueProcess = false;
					break;
			}
		}
	}

}
