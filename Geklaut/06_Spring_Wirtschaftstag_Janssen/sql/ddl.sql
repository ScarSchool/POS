CREATE TABLE IF NOT EXISTS User (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(256),
  name VARCHAR(128),
  pwd_token VARCHAR(256),
  type VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS Event (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  label VARCHAR(256),
  date DATE,
  default_price FLOAT,
  organiser_id INTEGER REFERENCES User
);

CREATE TABLE IF NOT EXISTS Company (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(256),
  zip_town VARCHAR(256),
  street VARCHAR(256),
  phone VARCHAR(256),
  email VARCHAR(256),
  reply_to VARCHAR(256),
  comments VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS Mail (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  at DATE,
  time TIME,
  subject VARCHAR(256),
  content VARCHAR(1024),
  attachment1 BLOB,
  attachment2 BLOB,
  attachment3 BLOB,
  sender_id INTEGER REFERENCES User
);

CREATE TABLE IF NOT EXISTS Department (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  label VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS Timeslot (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  starts TIME,
  ends TIME,
  max_participants INTEGER
);

CREATE TABLE IF NOT EXISTS Participation (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  price FLOAT,
  paid_already FLOAT,
  comments VARCHAR(256),
  at_id INTEGER REFERENCES Event,
  responsible_id INTEGER REFERENCES User,
  company_id INTEGER REFERENCES Company
);

CREATE TABLE IF NOT EXISTS Mail_receivers (
   mail_id INTEGER REFERENCES Mail,
   receivers_id INTEGER REFERENCES User,

   PRIMARY KEY(mail_id, receivers_id)
);

CREATE TABLE IF NOT EXISTS Participation_interested_in (
  participation_id INTEGER REFERENCES Participation,
  interested_in_id INTEGER REFERENCES Department,

  PRIMARY KEY(participation_id, interested_in_id)
);

CREATE TABLE IF NOT EXISTS Participation_slots (
  participation_id INTEGER REFERENCES Participation,
  slots_id INTEGER REFERENCES Timeslot,
  
  PRIMARY KEY(participation_id, slots_id)
);

CREATE TABLE IF NOT EXISTS User_joins (
  pupil_id INTEGER REFERENCES User,
  joins_id INTEGER REFERENCES Timeslot,
  
  PRIMARY KEY(pupil_id, joins_id)
);

COMMIT;
