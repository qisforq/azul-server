(ns venezuela.rpc.client
  (:import [foundation.paleblue.azul.proto AzulGrpc]
           [io.grpc ManagedChannelBuilder])
  (:require [venezuela.rpc.convert :as convert]
            [taoensso.timbre :as log]))


(defn make-client [port]
  (AzulGrpc/newBlockingStub
   (-> (io.grpc.ManagedChannelBuilder/forAddress "localhost" port)
       (.usePlaintext true)
       .build)))


(defn login
  [client username password]
  (let [request (convert/map->LoginRequest {:username username
                                            :password password})
        response (.userLogin client request)]
    (convert/LoginReply->map response)))

(defn register
  [client username password]
  (let [request (convert/map->RegisterRequest {:username username
                                               :password password})
        response (.register client request)]
    (convert/RegisterReply->map response)))

(defn balance
  [client session-token]
  (let [request (convert/map->CheckBalanceRequest {:session-token session-token})
        response (.checkBalance client request)]
    (convert/CheckBalanceReply->map response)))
