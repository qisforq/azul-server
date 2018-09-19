(ns venezuela.rpc.convert
  (:import [foundation.paleblue.azul.proto
            LoginReply
            LoginRequest
            LoginReply$Status])
  (:require [taoensso.timbre :as log]))

(defn map->LoginRequest
  ^LoginRequest
  [{:keys [username password]}]
  (-> (LoginRequest/newBuilder)
      (.setUsername username)
      (.setPassword password)
      .build))


(defn LoginRequest->map
  [^LoginRequest lr]
  {:username (.getUsername lr)
   :password (.getPassword lr)})


(defn map->LoginReply
  ^LoginReply
  [{:keys [success session-token message] :as m}]
  (let [reply (LoginReply/newBuilder)
        reply (if success
                (doto reply
                  (.setStatus LoginReply$Status/SUCCESS)
                  (.setSessionToken session-token))
                (doto reply
                  (.setStatus LoginReply$Status/FAILURE)
                  (.setMessage message)))]
    (.build reply)))

(defn LoginReply->map
  [^LoginReply lr]
  (if (= LoginReply$Status/SUCCESS (.getStatus lr))
    {:success true
     :session-token (.getSessionToken lr)}
    {:success false
     :message (.getMessage lr)}))
