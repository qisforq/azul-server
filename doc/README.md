# Getting started

## Set up dev environment

### Code

Navigate to the project's root.
Make sure to sync the submodules: 

	git submodule update
	git submodule sync

Install the app's dependencies:

	gradle generateProto

### Database

- Install Postgres
- Start postgres and connect as a super user
- Create tables:


	CREATE ROLE azul WITH LOGIN PASSWORD '';
	CREATE DATABASE azuldb_dev;

Check that auth works by querying the database:

	psql -d azuldb_dev --username=azul --command="select 'hello';"

Once that works, run migrations:

Open the lein repl:

	lein repl

Navigate to the persistence layer namespace:

	(migratus/migrate config)
    (require 'venezuela.db.persistence)
	(in-ns 'venezuela.db.persistence)

Look, no users yet:

	(users-all db)
	> ()

Create the test user:

	(insert-user-create db {:username "fenton travers!!!"})
	> 1
	> 
List the users to make sure new user exists:

	(users-all db)
	({:id 1, :username "fenton travers!!!", :created_at #inst "2018-09-12T18:20:23.794901000-00:00"})
    