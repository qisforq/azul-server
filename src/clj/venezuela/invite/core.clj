(ns venezuela.invite.core
  (:require [venezuela.sms.core :refer [send-sms]]
            [venezuela.localization.core :refer [make-message]]
            ))

(defn send-invitation [context phone-number]
  (send-sms [phone-number] (make-message (:locale context)) :invite))

(defn redeem-invitation [context ]

  )
