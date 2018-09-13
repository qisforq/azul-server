(ns venezuela.rpc.server
  (:import [foundation.paleblue.azul.proto
            LoginRequest
            LoginReply
            LoginReply$LoginReplyStatus]
           io.grpc.stub.StreamObserver
           [io.grpc Server ServerBuilder])
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]
            [venezuela.rpc.convert :as convert]))

;; XXX temp stub until merge
(defn login-stub
  [username password]
  (if (and (= "me" username) (= "12345" password))
    {:success true :session-token "12345"}
    {:success false :message "bad password"}))


(defn make-service
  []
  (proxy [foundation.paleblue.azul.proto.AzulGrpc$AzulImplBase] []
    (userLogin [^LoginRequest request
                ^StreamObserver responseObserver]
      (let [{:keys [username password]} (convert/LoginRequest->map request)
            login-result (login-stub username password)
            response (convert/map->LoginReply login-result)]
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
