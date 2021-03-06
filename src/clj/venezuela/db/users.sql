/*
This file contains hugsql functions that wrap postgresql queries.
See docs about hugsql sql files:
https://www.hugsql.org/#sql-file
*/

-- USERS ------------------------
-- :name all-users :? :*
-- :doc select all the users with all the attributes
SELECT *
FROM users

-- :name user-by-id :? :1
-- :doc Get user by id
select * from users
where id = :id

-- :name user-by-username :? :1
-- :doc Get user by username
select * from users
where username = :username

-- :name create-user :<!
-- :doc insert new user
INSERT INTO users (username, hashed_password, salt, created_at)
VALUES (:username, :hashed_password, :salt, current_timestamp) returning id

-- SESSIONS ------------------------
-- :name session-by-token :? :1
-- :doc get session by id
select * from sessions
where token = :token 
  and expired = false

-- :name create-session :<!
-- :doc insert new session
INSERT INTO sessions (user_id, token)
VALUES (:user_id, :token) returning token

-- :name user-by-token :? :1
-- :doc get session by id
select * from users
join sessions on sessions.user_id = users.id
where token = :token and expired = false

-- :name inactivate-session :! :n
-- :doc inactivate session by token
update sessions
  set expired = true
where token = :token

-- invite ------------------------
-- :name all-invites :? :*
-- :doc select all the invites
SELECT *
FROM invites

-- :name invite-by-id :? :1
-- :doc Get invite by id
select * from invites
where id = :id

-- :name create-invite :<!
-- :doc insert new invite
INSERT INTO invites (from_user, created_at)
VALUES (:from_user, current_timestamp) returning id

-- ACCEPTED INVITES ------------------------
-- all-accepted-invites
-- :name all-accepted-invites :? :*
-- :doc select all the accepted invites
SELECT *
FROM accepted_invites

-- :name accepted-invite-by-id :? :1
-- :doc Get accepted-invite by id
select * from accepted_invites
where id = :id

-- :name create-accepted-invite :<!
-- :doc insert new accepted-invite
INSERT INTO accepted_invites (invite_id, user_id, created_at)
VALUES (:invite_id, :user_id, current_timestamp) returning id

