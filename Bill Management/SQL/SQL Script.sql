DROP DATABASE IF EXISTS electroGridDB;

CREATE DATABASE electroGridDB;
USE electroGridDB;

SET @@SESSION.auto_increment_increment=1;

# Bill - NIC, Price, Date, Meter Reader

CREATE TABLE bill(
                     id INT NOT NULL AUTO_INCREMENT,
                     nic VARCHAR(100) NOT NULL,
                     price VARCHAR(100) NOT NULL,
                     date VARCHAR(100) NOT NULL,
                     meterReader VARCHAR(100) NOT NULL,

                     CONSTRAINT pk_bill PRIMARY KEY(id)
);

INSERT INTO bill(nic,price,date,meterReader)
VALUES
    ("5754756856V", "10000", "2022-01-31", "10000wh"),
    ("4553276890V", "50000", "2022-03-31", "50000wh"),
    ("6565653601V", "60000", "2022-04-31", "60000wh"),
    ("5767785562V", "40000", "2022-08-31", "40000wh"),
    ("8966533454V", "20000", "2022-06-31", "20000wh");
