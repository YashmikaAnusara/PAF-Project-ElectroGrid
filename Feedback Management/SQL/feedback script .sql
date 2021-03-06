DROP DATABASE IF EXISTS electroGridDB;

CREATE DATABASE electroGridDB;
USE electroGridDB;

SET @@SESSION.auto_increment_increment=1;

# Feedback - id, nic, Name, address, message
CREATE TABLE feedback(
                     id INT NOT NULL AUTO_INCREMENT,
                     nic VARCHAR(10) NOT NULL,
                     name VARCHAR(100) NOT NULL,
                     address VARCHAR(100) NOT NULL,
                     message VARCHAR(100) NOT NULL,
					 

                     CONSTRAINT pk_feedback PRIMARY KEY(id)
);

INSERT INTO feedback(id,nic,name,address,message)
VALUES
    ( "123455v", "Ahshan", "Trincomalee",'Nice'),
    ( "435667v", "Saneef", "Jaffna",'not bad'),
    ( "56788v", "Mahfoos", "Galle",'Thanks For Your Information'),
    ( "66789v", "Fayas", "Kandy",'Your Products Very Good'),
    ( "5667829v", "Rusthy", "Kuttikarachi",'Very Nice');