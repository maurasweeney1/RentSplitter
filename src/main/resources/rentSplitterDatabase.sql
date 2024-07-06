create schema rentSplitterUsers;

use rentSplitterUsers;

create table user (
	username VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL,
	FirstName VARCHAR(50) NOT NULL,
    PhoneNo VARCHAR(10) NOT NULL,
    isHome BOOLEAN NOT NULL,
    PRIMARY KEY (username, password)
);

create table bill (
    id INT AUTO_INCREMENT PRIMARY KEY,
	billName VARCHAR(20) NOT NULL,
    company VARCHAR(20) NULL,
    totalCost DOUBLE, 
    dateDueToCompany INT NOT NULL,
    dateDueToPayer INT NOT NULL,
    payer_id INT,
    StatementDate INT NULL,
    FOREIGN KEY (payer_id) REFERENCES Roommate (id)
);

create table houseGroup (
	id INT AUTO_INCREMENT PRIMARY KEY
);

create table groupMemberships (
	memberUsername VARCHAR(20) NOT NULL,
	memberPassword VARCHAR(20) NOT NULL,
    houseID INT,
    FOREIGN KEY (memberUsername, memberPassword) REFERENCES user (username, password),
    FOREIGN KEY (houseID) REFERENCES houseGroup(id)
);

create table hasBills (
	username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
	billID INT NOT NULL,
    FOREIGN KEY (username, password) REFERENCES user (username, password) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY (billID) REFERENCES bill (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- if roommate is deleted check if theyre payer and update --
