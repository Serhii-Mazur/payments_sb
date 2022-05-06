create schema mono;

create extension if not exists "uuid-ossp";

create table mono.users
(
	full_name varchar(50) not null,
	e_mail varchar(50) not null primary key,
	phone_number varchar(15) not null
)
;

create table mono.addresses
(
	id uuid not null primary key,
	address varchar(100) not null unique,
	user_email varchar(50) not null,
	foreign key (user_email)
    	references mono.users (e_mail)
    	on update cascade
    	on delete no action
)
;

create table mono.templates
(
	id uuid not null primary key,
	template_name varchar(100) not null,
	address_id uuid not null,
	payment_purpose varchar(200) not null,
	iban varchar(29) not null,
	foreign key (address_id)
    	references mono.addresses (id)
    	on update cascade
    	on delete no action
)
;

create table mono.payments
(
	id uuid not null primary key,
    template_id uuid not null,
    card_number bigint not null,
    payment_amount numeric(9,2) check(payment_amount > 0) not null,
    payment_status varchar(10) default 'NEW',
    created_date_time timestamp(0) default now(),
    etl_date_time timestamp(0) default now(),
    foreign key (template_id)
    	references mono.templates (id)
    	on update cascade
    	on delete no action
)
;