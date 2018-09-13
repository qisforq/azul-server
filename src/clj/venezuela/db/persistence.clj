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
  ;; USERS
  (def user-id
    (-> (create-user db {:username "frankie"}) first :id))
  (all-users db)
  (user-by-id db {:id user-id})

  ;; INVITES
  (def invite-id
    (-> (create-invite db {:from_user user-id}) first :id))
  (all-invites db)
  (invite-by-id db {:id invite-id})

  ;; ACCEPTED INVITES
  (def accepted-invite-id
    (-> (create-accepted-invite db {:invite_id invite-id :user_id user-id}) first :id))
  (all-accepted-invites db)
  (accepted-invite-by-id db {:id accepted-invite-id})

  ;; get the SQL output by functions
  (hugsql/def-sqlvec-fns "venezuela/db/users.sql")
  (insert-user-create-sqlvec {:username "fenton travers!!!"})
  (users-all-sqlvec))
