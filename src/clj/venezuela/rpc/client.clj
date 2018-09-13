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
  (let [login-request (convert/map->LoginRequest {:username username
                                                  :password password})
        resp (.userLogin client login-request)
        login-response (convert/LoginReply->map resp)]
    login-response))
