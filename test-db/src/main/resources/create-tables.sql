DROP TABLE IF EXISTS car;
CREATE TABLE car (
  carId INT NOT NULL AUTO_INCREMENT,
  registrationPlate VARCHAR(9) NOT NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY (carId)
);
DROP TABLE IF EXISTS crew;
CREATE TABLE crew (
  crewId INT NOT NULL AUTO_INCREMENT,
  crewName VARCHAR(255) NOT NULL,
  description VARCHAR(255) NULL,
  carId INT NOT NULL,
  PRIMARY KEY (crewId),
  FOREIGN KEY (carId) REFERENCES car (carId)
);
DROP TABLE IF EXISTS call;
CREATE TABLE call (
  callId INT NOT NULL AUTO_INCREMENT,
  dateCall DATE NOT NULL,
  description VARCHAR(255) NULL,
  address VARCHAR(255) NOT NULL,
  crewId int NOT NULL,
  PRIMARY KEY (callId),
  FOREIGN KEY (crewId) REFERENCES crew
);