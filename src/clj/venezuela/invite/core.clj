(ns venezuela.invite.core
  #_(:require [venezuela.sms.core :refer [send-sms]]
            [venezuela.invite.db :refer [id-activated? grant-incentive-and-activate-user!]]
            ))

#_(def app-download-url "https://play.google.com/store/apps/details?id=com.mycelium.wallet")
#_(def incentive-btc 0.001)

#_(defn invite-message [context]
  (format "Your friend %s invited you to join PBF. You'll both get %d BTC if you join. Get the app here: %s"
          (-> context :user :name)
          incentive-btc
          app-download-url))

#_(defn send-invitation! [context phone-number]
  (send-sms [phone-number] (invite-message context)))

#_(defn redeem-invitation! [context invitation-code]
  (let [gov-id (-> context
                   :user
                   :gov-id)]
    (if (id-activated? gov-id)
      :already-activated
      (grant-incentive-and-activate-user! context))))
