-- :name users-all
-- :command :query
-- :doc select all the users with all the attributes
SELECT *
FROM users

-- :name insert-user-create :! :n
-- :doc insert new user
INSERT INTO users (username, created_at)
VALUES (:username, current_timestamp)
