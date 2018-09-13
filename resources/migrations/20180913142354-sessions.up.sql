create table sessions (
  id serial primary key,
  user_id integer references users(id) not null,
  token varchar(255) unique not null,
  created_at timestamp not null default NOW(),
  expired boolean not null default false
);
