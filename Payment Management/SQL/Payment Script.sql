DROP DATABASE IF EXISTS electroGridDB;

CREATE DATABASE electroGridDB;
USE electroGridDB;

SET @@SESSION.auto_increment_increment=1;

# Payment - Pid, Cvv, Name, Date, Amount
CREATE TABLE payment(
                     id INT NOT NULL AUTO_INCREMENT,
                     payid VARCHAR(10) NOT NULL,
                     cvv VARCHAR(100) NOT NULL,
                     name VARCHAR(100) NOT NULL,
                     date VARCHAR(100) NOT NULL,
					 amount VARCHAR(100) NOT NULL,

                     CONSTRAINT pk_payment PRIMARY KEY(id)
);

INSERT INTO payment(payid,cvv,name,date,amount)
VALUES
    ("16V", "998", "Kamal Perera", "11/04/2022",10,000),
    ("11A", "563", "Nuwan Fernando", "16/04/2022",12,500),
    ("14C", "260", "Sunil Gunasena", "07/03/2022",11,000),
    ("23P", "405", "Amali Perra", "03/03/2022",5000),
    ("17V", "705", "Ann De silva", "08/03/2022",7800);
