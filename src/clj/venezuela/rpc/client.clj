(ns venezuela.rpc.client
  (:import [foundation.paleblue.azul.proto
            HelloReply
            HelloRequest
            AzulGrpc]
           [io.grpc ManagedChannelBuilder]))



(defn make-client [port]
  (AzulGrpc/newBlockingStub
   (-> (io.grpc.ManagedChannelBuilder/forAddress "localhost" port)
       (.usePlaintext true)
       .build)))


(defn say-hello
  [name]
  (-> (.sayHello (make-client 5001
                 (-> (HelloRequest/newBuilder)
                     (.setName name)
                     .build))
      .getMessage))

#_(def my-service (RemoteServices/createClient
                 HelloService
                 "localhost"
                 8081))


#_(defn get-balance []
  (.getBalance my-service))

#_(defn hello-world []
  (let [^HelloService helloService (RemoteServices/createClient
                                    HelloService
                                    "localhost"
                                    8081)]
    (println (-> helloService (.sayHello "David")))
    (println (-> helloService (.sayHelloAgain "DK")))))

#_(hello-world)


