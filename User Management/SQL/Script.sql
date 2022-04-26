DROP DATABASE IF EXISTS electroGridDB;

CREATE DATABASE electroGridDB;
USE electroGridDB;

SET @@SESSION.auto_increment_increment=1;

# User - Fname, Lname, Address, Email, Contact
CREATE TABLE user(
                     id INT NOT NULL AUTO_INCREMENT,
                     fname VARCHAR(100) NOT NULL,
                     lname VARCHAR(100) NOT NULL,
                     address VARCHAR(100) NOT NULL,
                     email VARCHAR(100) NOT NULL,
                     contact VARCHAR(10) NOT NULL,

                     CONSTRAINT pk_user PRIMARY KEY(id)
);

INSERT INTO user(fname,lname,address,email,contact)
VALUES
    ("dino", "crosetti", "italy", "dc@gmail.com", "784526541"),
    ("frank", "sinatra", "usa", "fs@gmil.com", "756845895"),
    ("thisakya", "anudini", "gampaha", "ta@gmail.com", "752416358"),
    ("thanura", "chamikara", "naragala", "tc@gmail.com", "754862159"),
    ("achintha", "kariyapperuma", "horana", "ak@gmail.com", "721485657");
