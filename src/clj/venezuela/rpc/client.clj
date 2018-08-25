(ns venezuela.client
  (:import
   [com.athaydes.protobuf.tcp.api RemoteServices]
   [venezuela.rpc HelloService]

   ))

(defn hello-world []
  (let [^HelloService helloService (RemoteServices/createClient
                                    HelloService
                                    "localhost"
                                    8081)]
    (println (-> helloService .sayHello "Joe"))
    (println (-> helloService .sayHelloAgain "Joe"))))
