(ns venezuela.push.core
  (:import [com.google.android.gcm.server Sender Message Message$Builder MulticastResult]
           ))

(def API_KEY "xxxxxxxxxxxxxxxxxxxxxxxx...vz") 
(def sender (Sender. API_KEY))

(defn notify
  "Send notification with the given message to the specific device"
  [message deviceRegId retryCount]
  (.send sender message deviceRegId retryCount))

(def devId "1jGmalO_K9bPrprKlH4...GTX9EtRDbCZDocdRR5qxxxxxx") ;; Get from your device during gcm registration
(def msg (-> (Message$Builder.)
             (.addData "key" "Updates")
             .build))

;; Look ma, no classes!
(notify msg devId 2)
