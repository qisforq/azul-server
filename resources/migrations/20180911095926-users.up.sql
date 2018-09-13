create table users (
  id serial primary key,
  username varchar(255) unique,
  created_at timestamp
);
