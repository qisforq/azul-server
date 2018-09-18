(ns venezuela.auth.login
  (:require [buddy.hashers :as hashers]
            [venezuela.db.persistence :as db]
            [venezuela.auth.sessions :as sessions]))

(defn login [username password]
    (let [user (db/user-by-username db/db {:username username})
          hashed-password (:hashed_password user)]
      (if (nil? user)
          {:success false :message "User doesn't exist"}
          (if (hashers/check (str password (:salt user)) hashed-password)
             {:success true :session-token (sessions/create (:id user) hashed-password)}
             {:success false :message "Invalid password"}))))

(defn logout [session-token]
  (db/inactivate-session db/db {:token session-token}))
