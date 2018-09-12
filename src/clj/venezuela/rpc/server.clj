(ns venezuela.rpc.server
  (:import [foundation.paleblue.azul.proto HelloReply HelloRequest]
           io.grpc.stub.StreamObserver
           [io.grpc Server ServerBuilder])
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]))
  ;; #_(:import [com.athaydes.protobuf.tcp.api RemoteServices]
  ;;          [java.io Closeable]
  ;;          [venezuela.rpc HelloService]))


(defn make-service
  []
  (proxy [foundation.paleblue.azul.proto.AzulGrpc$AzulImplBase] []
    (sayHello [^HelloRequest request
               ^StreamObserver responseObserver]
      (log/info "sayHello called with request: " request)
      (let [name (.getName request)
            response (-> (HelloReply/newBuilder)
                         (.setMessage (str "Hello " name)))]
        (.onNext responseObserver (.build response))
        (.onCompleted responseObserver)))))


(defn start-server [port]
  (let [builder (ServerBuilder/forPort port)
        service (make-service)
        _ (.addService builder service)
        server (.build builder)]
    (.start server)
    server))


(defn stop-server [server]
  (when server (.shutdown server)))
