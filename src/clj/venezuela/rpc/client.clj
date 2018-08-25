(ns venezuela.rpc.client
  (:import
   [com.athaydes.protobuf.tcp.api RemoteServices]
   [venezuela.rpc HelloService]

   ))

(def my-service (RemoteServices/createClient
                 HelloService
                 "localhost"
                 8081))


(defn get-balance []
  (.getBalance my-service))

(defn hello-world []
  (let [^HelloService helloService (RemoteServices/createClient
                                    HelloService
                                    "localhost"
                                    8081)]
    (println (-> helloService (.sayHello "David")))
    (println (-> helloService (.sayHelloAgain "DK")))))

(hello-world)


