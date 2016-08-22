CREATE DATABASE hibernatetwo;

CREATE TABLE Person (
	personId int not null,
	firstName varchar(255),
	middleName varchar(255),
	lastName varchar(255), 
	suffix varchar(255), 
	title varchar(255),
	birthDate date,
	employed boolean,
	gwa real,
	dateHired date,
	addressId int,
	primary key (personId));

CREATE TABLE Address (
	id bigint not null, 
	houseNo int,
	street varchar(255),
	barangay varchar(255), 
	city varchar(255), 
	zipCode int,
	primary key (id));

CREATE TABLE Contact (
	personId int,
	contactId bigint not null,
	contactType varchar(255), 
	contactValue varchar(255),
	index int,
	primary key (contactId));


CREATE TABLE Role (
	Id int not null,
	roleName varchar(255), 
	primary key (roleId));

CREATE TABLE person_role (
	personId int not null,
	roleId int not null,
	primary key (personId, roleId));

INSERT INTO role VALUES(1, 'Trainee');
INSERT INTO role VALUES(2, 'Trainor');
INSERT INTO role VALUES(3, 'QA Tester');
INSERT INTO role VALUES(4, 'Developer');
INSERT INTO role VALUES(5, 'UI');