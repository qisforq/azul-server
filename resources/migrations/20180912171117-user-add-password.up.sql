ALTER TABLE users
  ADD COLUMN hashed_password varchar(255),
  ADD COLUMN salt varchar(255);
