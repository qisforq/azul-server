(ns venezuela.auth.login
  (:require [buddy.hashers :as hashers]
            [venezuela.db.persistence :as db]))


(defn login [username password]
    (let [user (db/user-by-username db/db {:username username})]
      (if (nil? user)
          {:success false :message "User doesn't exist"}
          (if (hashers/check (str password (:salt user)) (:hashed_password user))
             {:success true :session-token (str "123" (:id user))}
             {:success false :message "Invalid password"}))))

(defn create [username password]
  "TODO: return session id instead of user id"
  (if (db/user-by-username db/db {:username username})
    {:success false :message "User already exists"}

  (let [salt "123"
        salted-password  (str password salt)
        hashed-password (hashers/derive salted-password)
        user-id (-> (db/create-user db/db {:username username 
                            :hashed_password hashed-password :salt salt})
                      first
                      :id)]
        {:success true :session-token (str "123" user-id)}
    )
))
