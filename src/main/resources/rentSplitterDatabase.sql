create schema rentSplitterUsers;

use rentSplitterUsers;

create table user (
	username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (username, password)
);

create table roommate (
	FirstName VARCHAR(50) PRIMARY KEY,
    PhoneNo VARCHAR(10) NOT NULL,
    isHome BOOLEAN NOT NULL
);

create table bill (
	billName VARCHAR(20) PRIMARY KEY,
    company VARCHAR(20) NULL,
    totalCost DOUBLE, 
    dateDueToCompany INT NOT NULL,
    dateDueToPayer INT NOT NULL,
    payerName VARCHAR(20), -- FIXME idk what to do about that --
    StatementDate INT NULL
);

create table hasRoommates (
	username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    FOREIGN KEY (username, password) REFERENCES user (username, password) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY (FirstName) REFERENCES roommate (FirstName) ON DELETE NO ACTION ON UPDATE NO ACTION
);

create table hasBills (
	username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
	billName VARCHAR(20) NOT NULL,
    FOREIGN KEY (username, password) REFERENCES user (username, password) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY (billName) REFERENCES bill (billName) ON DELETE NO ACTION ON UPDATE NO ACTION
);