package com.exercise.hibernate1;

import com.exercise.hibernate1.core.*;
import java.util.Scanner;

public class App{

	public static void main(String [] args){
		Validation check = new Validation();
		Scanner read= new Scanner(System.in);
		boolean processContinue = true;
		while(processContinue==true){
			displayMainMenu();
		  int choice=check.inputNumber("Choice: ");
			switch(choice){
				case 1:
						PersonMenu pMenu = new PersonMenu();
						pMenu.menu();
						break;
				case 2:
						RoleMenu rMenu = new RoleMenu();
						rMenu.menu();
						break;
				case 3:
						ContactMenu cMenu = new ContactMenu();
						cMenu.menu();
						break;
				default:
						break;
			}
		}
	}

	public static void displayMainMenu(){
		System.out.println("\n\t  =============== MAIN MENU ================");
		System.out.println("\t  | Choose from the following options      |");
		System.out.println("\t  |    1  - Person Management              |");
		System.out.println("\t  |    2  - Role Management                |");
		System.out.println("\t  |    3  - Contacts Management            |");
		System.out.println("\t  ==========================================\n");
	}



 }
