(ns venezuela.auth.login
  (:require [buddy.hashers :as hashers]
            [venezuela.db.persistence :as db]
            [venezuela.auth.sessions :as sessions]
            [java-time :as jt]))


(defn login [username password]
    (let [user (db/user-by-username db/db {:username username})
          hashed-password (:hashed_password user)]
      (if (nil? user)
          {:success false :message "User doesn't exist"}
          (if (hashers/check (str password (:salt user)) hashed-password)
             {:success true :session-token (sessions/create (:id user) hashed-password)}
             {:success false :message "Invalid password"}))))

(defn create [username password]
  (if (db/user-by-username db/db {:username username})
    {:success false :message "User already exists"}

  (let [salt (str (hash (jt/local-time)))
        salted-password  (str password salt)
        hashed-password (hashers/derive salted-password)
        user-id (-> (db/create-user db/db {:username username 
                            :hashed_password hashed-password :salt salt})
                      first
                      :id)]
        {:success true :session-token (sessions/create user-id hashed-password)}
    )
))

(defn logout [session-token]
  (db/inactivate-session db/db {:token session-token}))