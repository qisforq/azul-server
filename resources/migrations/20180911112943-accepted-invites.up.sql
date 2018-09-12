create table accepted_invites (
  id serial primary key,
  user_id integer references users(id),
  invite_id integer references invites(id),
  created_at timestamp
);
