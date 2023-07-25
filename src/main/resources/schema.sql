CREATE TABLE restaurant (
  id         LONG IDENTITY AUTO_INCREMENT,
  name 		 VARCHAR(30) NOT NULL,
  address	 VARCHAR (200) NOT NULL,
  city       VARCHAR (200) NOT NULL,
  state      VARCHAR (20) NOT NULL,
  zipCode    VARCHAR (10) NOT NULL,
  phone 	 VARCHAR(15) NOT NULL,
  email      VARCHAR (50) NOT NULL
);