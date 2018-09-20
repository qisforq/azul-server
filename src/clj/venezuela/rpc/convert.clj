(ns venezuela.rpc.convert
  (:import [foundation.paleblue.azul.proto
            LoginReply
            LoginRequest
            LoginReply$Status
            RegisterRequest
            RegisterReply
            RegisterReply$Status
            CheckBalanceRequest
            CheckBalanceReply])
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


(defn map->RegisterRequest
  ^RegisterRequest
  [{:keys [username password]}]
  (-> (RegisterRequest/newBuilder)
    (.setUsername username)
    (.setPassword password)
    .build))

(defn RegisterRequest->map
  [^RegisterRequest lr]
  {:username (.getUsername lr)
   :password (.getPassword lr)})

(defn map->RegisterReply
  ^RegisterReply
  [{:keys [success session-token message] :as m}]
  (let [reply (RegisterReply/newBuilder)
        reply (if success
                (doto reply
                  (.setStatus RegisterReply$Status/SUCCESS)
                  (.setSessionToken session-token))
                (doto reply
                  (.setStatus RegisterReply$Status/FAILURE)
                  (.setMessage message)))]
    (.build reply)))

(defn RegisterReply->map
  [^RegisterReply lr]
  (if (= RegisterReply$Status/SUCCESS (.getStatus lr))
    {:success true
     :session-token (.getSessionToken lr)}
    {:success false
     :message (.getMessage lr)}))

(defn map->CheckBalanceRequest
  ^CheckBalanceRequest
  [{:keys [session-token]}]
  (-> (CheckBalanceRequest/newBuilder)
    (.setSessionToken session-token)
    .build))

(defn CheckBalanceRequest->map
  [^CheckBalanceRequest request]
  {:session-token (.getSessionToken request)})

(defn map->CheckBalanceReply
  ^CheckBalanceReply
  [{:keys [satoshis]}]
  (let [reply (CheckBalanceReply/newBuilder)
       reply (doto reply
               (.setSatoshis satoshis))]
   (.build reply)))

(defn CheckBalanceReply->map
  [^CheckBalanceReply reply]
  {:satoshis (.getSatoshis reply)
   :updated-at (.getUpdatedAt reply)})
