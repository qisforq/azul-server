(ns venezuela.auth.registration
  (:require [buddy.hashers :as hashers]
            [venezuela.db.persistence :as db]
            [venezuela.auth.sessions :as sessions]
            [java-time :as time]))

(defn register [username password]
  (if (db/user-by-username db/db {:username username})
    {:success false :message "User already exists"}

  (let [salt (str (hash (time/local-time)))
        salted-password  (str password salt)
        hashed-password (hashers/derive salted-password)
        user-id (-> (db/create-user db/db {:username username
                            :hashed_password hashed-password :salt salt})
                      first
                      :id)]
        {:success true :session-token (sessions/create user-id hashed-password)}
    )
))
