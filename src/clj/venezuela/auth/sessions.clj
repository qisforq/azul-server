(ns venezuela.auth.sessions
  (:require [venezuela.db.persistence :as db]
            [buddy.sign.jws :as jws]
            [java-time :as jt]))

(defn create [user-id, user-secret]
    (let [token (jws/sign (str (jt/local-time)) user-secret)]
      (-> (db/create-session db/db {:user_id user-id :token token}) first :token)
    ))

(comment 
  ; get a user from a session:
  (db/user-by-token db/db {:token session})
  ; get a user_id from a session
  (:user_id (db/session-by-token db/db {:token session}))
)