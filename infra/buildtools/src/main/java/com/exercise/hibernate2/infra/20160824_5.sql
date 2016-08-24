CREATE DATABASE hibernatetwo;

\c hibernatetwo;


create table Address (
	id  bigserial not null, 
	houseNo int, 
	street varchar(255),
	barangay varchar(255), 
	city varchar(255),  
	zipCode int, 
	primary key (id));

create table Person (
	id  bigserial not null, 
	firstName varchar(255), 
	middleName varchar(255),
	lastName varchar(255), 
	suffix varchar(255), 
	title varchar(255), 
	dateHired date,
	gwa real, 
	employed varchar(255), 
	birthDate date, 
	addressId int, 
	primary key (id),
	constraint FK_Address foreign key (addressId) references Address);

create table Contact (
	id  bigserial not null, 
	contactType varchar(255), 
	contactValue varchar(255), 
	personId int8, primary key (id),
	constraint FK_Person foreign key (personId) references Person);

create table Role (
	id  bigserial not null, 
	roleName varchar(255), 
	primary key (id));

create table person_role (
	personId int not null, 
	roleId int not null, 
	primary key (personId, roleId),
	constraint FK_Role foreign key (roleId) references Role),
	constraint FK_Person foreign key (personId) references Person);

insert into Role (roleName) values ('Trainee');
insert into Role (roleName) values ('Developer');
insert into Role (roleName) values ('QA');
insert into Role (roleName) values ('UI');
insert into Role (roleName) values ('Manager');