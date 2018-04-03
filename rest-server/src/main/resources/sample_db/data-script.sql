INSERT INTO car (registrationPlate, description)
VALUES ('3347 AO-1', 'Paramedics');
INSERT INTO car (registrationPlate, description)
VALUES ('5569 BC-1', 'Emergency care');
INSERT INTO crew (crewName, description, carId)
VALUES ('Crew1', 'Some Crew1', 1);
INSERT INTO crew (crewName, description, carId)
VALUES ('Crew2', 'Some Crew2', 2);
INSERT INTO crew (crewName, description, carId)
VALUES ('Crew3', 'Some Crew3', 2);
INSERT INTO call (dateCall, description, address, crewId)
VALUES ('2018-03-27', 'Some description1', 'Some address1', 2);
INSERT INTO call (dateCall, description, address, crewId)
VALUES ('2018-03-23', 'Some description2', 'Some address2', 1);
INSERT INTO call (dateCall, description, address, crewId)
VALUES ('2018-02-23', 'Some description3', 'Some address3', 1);
