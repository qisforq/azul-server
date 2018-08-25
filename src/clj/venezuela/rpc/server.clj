(ns venezuela.server
  (:import [com.athaydes.protobuf.tcp.api RemoteServices]
           [java.io Closeable]
           [venezuela.rpc HelloService]
   ))

(defn create-server []
  (let [^Closeable server (RemoteServices/provideService
                           (reify HelloService
                             (sayHello [name]
                               (str "Hello " name))
                             (sayHelloAgain [name]
                               (str "Hello again, " name)))
                           8081
                           HelloService)]
    (println "Type something to stop the server" )
    (read-line)
    (.close server)))
