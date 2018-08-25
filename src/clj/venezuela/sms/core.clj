(ns venezuela.sms.core
  (:require [twijio.api :refer [send-message]]
   ;;[twijio.http :refer [twilio-request!]]
   ))

(def config
  {:twilio-account "your-twilio-account-sid"
   :twilio-token "tour-twilio-token"})

(defn send-sms [numbers message]
  ;; TODO: Add Twilio support
  ;;(twilio/send-message config {:to "+15555555555" :from "+12345678901" :body "Hello World!"})
  :unimplemented)
