create table invites (
  id integer primary key,
  from_user integer references users(id),
  created_at timestamp
);
