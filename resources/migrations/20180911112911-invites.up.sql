create table invites (
  id serial primary key,
  from_user integer references users(id),
  created_at timestamp
);
