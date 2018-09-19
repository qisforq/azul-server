(ns venezuela.auth.registration
  (:require [buddy.hashers :as hashers]
            [venezuela.db.persistence :as db]
            [venezuela.auth.sessions :as sessions]
            [java-time :as time]))

(defn register [username password]
  (cond
    (empty? username)
      {:success false :message "Username is required"}
    (> 3 (count username))
      {:success false :message "Username is too short, min length: 3"}
    (empty? password)
      {:success false :message "Password is required"}
    (db/user-by-username db/db {:username username})
      {:success false :message "User already exists"}
    :else
      (let [salt (str (hash (time/local-time)))
            salted-password  (str password salt)
            hashed-password (hashers/derive salted-password)
            user-id (-> (db/create-user db/db {:username username
                                :hashed_password hashed-password :salt salt})
                          first
                          :id)]
            {:success true :session-token (sessions/create user-id hashed-password)}
        )))
