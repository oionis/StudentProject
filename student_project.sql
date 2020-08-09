DROP TABLE IF EXISTS sp_student_child;
DROP TABLE IF EXISTS sp_student_order;
DROP TABLE IF EXISTS sp_university;
DROP TABLE IF EXISTS sp_passport_office;
DROP TABLE IF EXISTS sp_register_office;
DROP TABLE IF EXISTS sp_country_struct;
DROP TABLE IF EXISTS sp_street;

CREATE TABLE sp_street (
	street_code INTEGER NOT NULL,
	street_name varchar(100),
	PRIMARY KEY (street_code)
);

CREATE TABLE sp_university (
	university_id INTEGER NOT NULL,
	university_name varchar(100),
	PRIMARY KEY (university_id)
);

CREATE TABLE sp_country_struct (
	area_id char(12)  NOT NULL,
	area_name varchar (200),
	PRIMARY KEY (area_id)
);

CREATE TABLE sp_passport_office (
	p_office_id INTEGER NOT NULL,
	p_office_area_id char(12) NOT NULL,
	p_office_name varchar(200),
	PRIMARY KEY (p_office_id),
	FOREIGN KEY (p_office_area_id) REFERENCES sp_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE sp_register_office (
	r_office_id INTEGER NOT NULL,
	r_office_area_id char(12) NOT NULL,
	r_office_name varchar(200),
	PRIMARY KEY (r_office_id),
	FOREIGN KEY (r_office_area_id) REFERENCES sp_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE sp_student_order (
    student_order_id SERIAL,
    student_order_date timestamp not null,
    student_order_status integer not null,

    h_surname varchar(100) NOT NULL,
    h_given_name varchar(100) NOT NULL,
    h_patronymic varchar(100) NOT NULL,
    h_date_of_birth date NOT NULL,
    h_post_index varchar(10),
    h_street_code integer NOT NULL,
    h_building varchar(10) NOT NULL,
    h_apartment varchar(10),
    h_passport_seria varchar(10) not null,
    h_passport_number varchar(10) not null,
    h_passport_date date not null,
    h_passport_office_id integer not null,
    h_university_id integer not null,
    h_university_number varchar(30) not null,



    w_surname varchar(100) NOT NULL,
    w_given_name varchar(100) NOT NULL,
    w_patronymic varchar(100) NOT NULL,
    w_date_of_birth date NOT NULL,
    w_post_index varchar(10),
    w_street_code integer NOT NULL,
    w_building varchar(10) NOT NULL,
    w_apartment varchar(10),
    w_passport_seria varchar(10) not null,
    w_passport_number varchar(10) not null,
    w_passport_date date not null,
    w_passport_office_id integer not null,
    w_university_id integer not null,
    w_university_number varchar(30) not null,

    certificate_id varchar(20) NOT NULL,
    register_office_id integer NOT NULL,
    marriage_date date NOT NULL,

    PRIMARY KEY (student_order_id),
    FOREIGN KEY (h_street_code) REFERENCES sp_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (h_passport_office_id) REFERENCES sp_passport_office (p_office_id) ON DELETE RESTRICT,
    FOREIGN KEY (h_university_id) REFERENCES sp_university (university_id) ON DELETE RESTRICT,
    FOREIGN KEY (w_passport_office_id) REFERENCES sp_passport_office (p_office_id) ON DELETE RESTRICT,
    FOREIGN KEY (w_university_id) REFERENCES sp_university (university_id) ON DELETE RESTRICT,
    FOREIGN KEY (w_street_code) REFERENCES sp_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (register_office_id) REFERENCES sp_register_office(r_office_id) ON DELETE RESTRICT

);

CREATE TABLE sp_student_child (

    student_child_id SERIAL,
    student_order_id integer not null,

    c_surname varchar(100) NOT NULL,
    c_given_name varchar(100) NOT NULL,
    c_patronymic varchar(100) NOT NULL,
    c_date_of_birth date NOT NULL,
    c_post_index varchar(10),
    c_street_code integer NOT NULL,
    c_building varchar(10) NOT NULL,
    c_apartment varchar(10),

    c_certificate_number varchar(10) not null,
    c_certificate_date date not null,
    c_register_office_id integer not null,

    PRIMARY KEY (student_child_id),
    FOREIGN KEY (c_street_code) REFERENCES sp_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (c_register_office_id) REFERENCES sp_register_office(r_office_id) ON DELETE RESTRICT


);



-- INSERT INTO sp_street (street_code, street_name) VALUES (1, 'Street first'),
-- (2, 'Second street'), (3, 'Third street')

-- UPDATE sp_street SET street_name = 'Test2' WHERE street_code = 1 or street_code = 3

-- DELETE FROM sp_street WHERE street_code = 1

-- SELECT street_code FROM (SELECT street_code, street_name FROM sp_street WHERE street_code in (1, 3)) s1;

-- SELECT street_code sc, street_name FROM sp_street WHERE street_code in (1, 3, 5, 6) order by sc DESC -- or ASC
