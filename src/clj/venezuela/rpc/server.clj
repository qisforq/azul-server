(ns venezuela.rpc.server
  (:import [foundation.paleblue.azul.proto
            LoginRequest
            RegisterRequest]
           io.grpc.stub.StreamObserver
           [io.grpc Server ServerBuilder])
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]
            [venezuela.rpc.convert :as convert]
            [venezuela.auth.registration :as registration]
            [venezuela.auth.login :as login]))

(defn make-service
  []
  (proxy [foundation.paleblue.azul.proto.AzulGrpc$AzulImplBase] []
    (userLogin [^LoginRequest request
                ^StreamObserver responseObserver]
      (let [{:keys [username password]} (convert/LoginRequest->map request)
            response (convert/map->LoginReply (login/login username password))]
        (.onNext responseObserver response)
        (.onCompleted responseObserver)))
    (register [^RegisterRequest request
               ^StreamObserver responseObserver]
      (let [{:keys [username password]} (convert/RegisterRequest->map request)
            response (convert/map->RegisterReply (registration/register username password))]
        (.onNext responseObserver response)
        (.onCompleted responseObserver)))))


(defn start-server [port]
  (let [builder (ServerBuilder/forPort port)
        service (make-service)
        _ (.addService builder service)
        server (.build builder)]
    (.start server)
    server))


(defn stop-server [server]
  (when server (.shutdown server))
  server)
