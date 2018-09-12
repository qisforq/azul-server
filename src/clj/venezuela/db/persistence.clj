(ns venezuela.db.persistence
  (:require [hugsql.core :as hugsql]))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost/azuldb_dev"
   :user "azul"
   :password ""})

(hugsql/def-db-fns "venezuela/db/users.sql")

(comment
  (users-all db)
  (insert-user-create db {:username "fenton travers!!!"}))
