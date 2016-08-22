CREATE DATABASE hibernatetwo;

\c hibernatetwo;

create table Person (
	id  bigserial not null, 
	firstName varchar(255),
	middleName varchar(255), 
	lastName varchar(255), 
	suffix varchar(255), 
	title varchar(255), 
	birthDate date, 
	employed varchar(255),
	gwa real, 
	dateHired date, 
	addressId int, 
	primary key (id));

create table Address (
	id  bigserial not null,
	houseNo int, 
	street varchar(255),  
	barangay varchar(255), 
	city varchar(255), 
	zipCode int, 
	primary key (id));

create table Contact (
	id  bigserial not null, 
	contactType varchar(255), 
	contactValue varchar(255), 
	personId int, 
	primary key (id));

create table Role (
	id  bigserial not null, 
	roleName varchar(255), 
	primary key (id));

create table person_role (
	personId int not null, 
	roleId int not null, 
	primary key (personId, roleId));

INSERT INTO role VALUES(1, 'Trainee');
INSERT INTO role VALUES(2, 'Trainor');
INSERT INTO role VALUES(3, 'QA');
INSERT INTO role VALUES(4, 'Developer');
INSERT INTO role VALUES(5, 'UI');
