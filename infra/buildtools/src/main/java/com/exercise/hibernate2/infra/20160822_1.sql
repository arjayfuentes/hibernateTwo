CREATE DATABASE hibernatetwo;

CREATE TABLE Person (
	personId int not null,
	birthDate date,
	dateHired date,
	employed boolean,
	firstName varchar(255), 
	gwa float,
	lastName varchar(255), 
	middleName varchar(255), 
	suffix varchar(255), 
	title varchar(255), 
	addressId int,
	primary key (personId));


CREATE TABLE Address (
	addressId bigint not null,
	barangay varchar(255), 
	city varchar(255), 
	houseNo int,
	street varchar(255), 
	zipCode int,
	primary key (addressId));

CREATE TABLE Contact (
	contactId bigint not null,
	contactType varchar(255), 
	contactValue varchar(255),
	index int,
	personId int,
	primary key (contactId));


CREATE TABLE Role (
	roleId int not null,
	roleName varchar(255), 
	primary key (roleId));

CREATE TABLE person_role (
	personId int not null,
	roleId int not null,
	primary key (personId, roleId));
