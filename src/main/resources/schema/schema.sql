CREATE DATABASE grama_vista;
SHOW DATABASES;
USE grama_vista;
CREATE TABLE gn_division(
                            division_id VARCHAR(10),
                            name VARCHAR(20),
                            div_secretariat VARCHAR(20) DEFAULT 'Bope Poddala',
                            admin_officer VARCHAR(30),
                            population INT(5),
                            land_area VARCHAR(10),
                            CONSTRAINT PRIMARY KEY (division_id)
);

CREATE TABLE residence (
                           home_id VARCHAR(10),
                           division_id VARCHAR(10),
                           house_holder VARCHAR(30),
                           address VARCHAR(100),
                           member_count INT,
                           count_below_18 INT,
                           residence_type ENUM('Full built','Half built','Temporary'),
                           electricity ENUM('Yes','No'),
                           water_supply ENUM('Yes','No'),
                           CONSTRAINT PRIMARY KEY (home_id),
                           CONSTRAINT FOREIGN KEY (division_id) REFERENCES gn_division(division_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE civil(
                      reg_number INT AUTO_INCREMENT,
                      nic VARCHAR(12),
                      name VARCHAR(50),
                      address VARCHAR(100),
                      gender ENUM('Male','Female','Other'),
                      dob DATE,
                      marriage_status ENUM ('Married','Not married'),
                      relation VARCHAR(20),
                      education_status ENUM ('Student','Employed','Unemployed'),
                      school VARCHAR(30),
                      occupation VARCHAR(30),
                      working_address VARCHAR(1000),
                      salary DOUBLE (8,2),
                      image longblob,
                      CONSTRAINT PRIMARY KEY (reg_number)
);

CREATE TABLE multi_residence(
                                home_id VARCHAR(10),
                                reg_number INT,
                                CONSTRAINT FOREIGN KEY (home_id) REFERENCES residence(home_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                CONSTRAINT FOREIGN KEY (reg_number) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE dead_people(
                            id INT AUTO_INCREMENT,
                            reg_number INT UNIQUE,
                            dead_date DATE,
                            CONSTRAINT PRIMARY KEY (id),
                            CONSTRAINT FOREIGN KEY (reg_number) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE disable_people(
                               id INT AUTO_INCREMENT,
                               reg_number INT UNIQUE,
                               disability VARCHAR(50),
                               description VARCHAR(100),
                               CONSTRAINT PRIMARY KEY (id),
                               CONSTRAINT FOREIGN KEY (reg_number) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE maternity_people(
                                 id INT AUTO_INCREMENT,
                                 reg_number INT UNIQUE,
                                 date_of_pregnancy DATE,
                                 months INT,
                                 mid_wife VARCHAR(40),
                                 CONSTRAINT PRIMARY KEY (id),
                                 CONSTRAINT FOREIGN KEY (reg_number) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE contact(
                        reg_number INT,
                        contact_num INT(10),
                        CONSTRAINT FOREIGN KEY (reg_number) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE land(
                     land_num VARCHAR(10),
                     plan_num VARCHAR(20),
                     land_area VARCHAR(10),
                     CONSTRAINT PRIMARY KEY (land_num)
);

CREATE TABLE co_ownership(
                             reg_number INT,
                             land_num INT,
                             land_percentage VARCHAR(4),
                             lot_num VARCHAR(10),
                             CONSTRAINT FOREIGN KEY (reg_number) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE,
                             CONSTRAINT FOREIGN KEY (land_num) REFERENCES land(land_num) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE land_type (
                           type_id INT AUTO_INCREMENT,
                           name ENUM('Government','Non Government','Cultivated', 'Uncultivated'),
                           CONSTRAINT PRIMARY KEY (type_id)
);

CREATE TABLE land_detail (
                             type_id INT ,
                             land_num INT,
                             CONSTRAINT FOREIGN KEY (land_num) REFERENCES land(land_num) ON UPDATE CASCADE ON DELETE CASCADE,
                             CONSTRAINT FOREIGN KEY (type_id) REFERENCES land_type(type_id) ON UPDATE CASCADE ON DELETE CASCADE

);

SHOW TABLES ;

CREATE TABLE candidate (
                           elect_reg_num VARCHAR(10),
                           division_id VARCHAR(10),
                           nic VARCHAR(12),
                           name VARCHAR(30),
                           age INT(2),
                           CHECK ( age>18 ),
                           address VARCHAR(100),
                           contact INT(10),
                           political_party VARCHAR(50),
                           votes INT(4),
                           image longblob,
                           CONSTRAINT PRIMARY KEY (elect_reg_num),
                           CONSTRAINT FOREIGN KEY (division_id) REFERENCES gn_division(division_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE users (
                       employee_num VARCHAR(10),
                       division_id VARCHAR(10) UNIQUE,
                       nic VARCHAR(12),
                       name VARCHAR(30),
                       user VARCHAR(20),
                       password VARCHAR(20),
                       dob DATE,
                       date_of_employment DATE,
                       salary DOUBLE (8,2),
                       contact INT(10),
                       CONSTRAINT PRIMARY KEY (employee_num),
                       CONSTRAINT FOREIGN KEY (division_id) REFERENCES gn_division(division_id) ON UPDATE CASCADE ON DELETE CASCADE
);

DELETE FROM gn_division WHERE division_id = 'DN02';

CREATE TABLE detail(
                       detail_num INT AUTO_INCREMENT,
                       function_name VARCHAR(50),
                       user VARCHAR(50),
                       id VARCHAR(10),
                       name VARCHAR(50),
                       time TIME,
                       date DATE,
                       CONSTRAINT PRIMARY KEY (detail_num)
);

CREATE TABLE voteDTO(
                     election_id VARCHAR(10),
                     civil_id INT(10),
                     candidate_id VARCHAR(10),
                     CONSTRAINT FOREIGN KEY (election_id) REFERENCES voteReg(election_id) ON UPDATE CASCADE ON DELETE CASCADE,
                     CONSTRAINT FOREIGN KEY (civil_id) REFERENCES civil(reg_number) ON UPDATE CASCADE ON DELETE CASCADE,
                     CONSTRAINT FOREIGN KEY (candidate_id) REFERENCES candidate(elect_reg_num) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE voteReg (
                         election_id VARCHAR(10),
                         election_type ENUM ('Local Authorities Election'),
                         candidate_count INT(3),
                         CONSTRAINT PRIMARY KEY (election_id)
);

CREATE TABLE add_candidate(
                              election_id VARCHAR(10),
                              candidate_id VARCHAR(10),
                              CONSTRAINT FOREIGN KEY (election_id) REFERENCES voteReg(election_id) ON UPDATE CASCADE ON DELETE CASCADE
);

