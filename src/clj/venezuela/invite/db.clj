(ns venezuela.invite.db
  #_(:require [hashids.core :as h]
            [taoensso.faraday :as far]
            [venezuela.env.rand :refer [uuid]]
            [venezuela.env.time :refer [now]]))

#_(def hashids-opts {:salt "this is my salt"})

#_(def client-opts
  {;;; For DDB Local just use some random strings here, otherwise include your
   ;;; production IAM keys:
   :access-key "<AWS_DYNAMODB_ACCESS_KEY>"
   :secret-key "<AWS_DYNAMODB_SECRET_KEY>"

   ;;; You may optionally override the default endpoint if you'd like to use DDB
   ;;; Local or a different AWS Region (Ref. http://goo.gl/YmV80o), etc.:
   ;; :endpoint "http://localhost:8000"                   ; For DDB Local
   ;; :endpoint "http://dynamodb.eu-west-1.amazonaws.com" ; For EU West 1 AWS region
   })

#_(def inv-table :invitations)

#_(defn create-invitation! [context]
  (loop []
    (let [invite-code (h/encode hashids-opts (uuid))]
      (if (far/get-item client-opts inv-table {:invite-code invite-code})
        (recur)
        (far/put-item
         client-opts
         inv-table
         {:invite-code invite-code
          ;;:phone-number phone-num
          :status :pending
          :originator (-> context :user :gov-id)
          :created-at (now)})))))

#_(defn id-activated? [gov-id]
  ;; TODO: check the result returned
  (far/get-item client-opts inv-table {:gov-id gov-id}))

#_(defn grant-incentive-and-activate-user! [context]

  ;; Question: how are we representing users?

  ;; Pay the originator

  ;; Pay the invitee

  ;; Mark the invitation as consumed

  )
