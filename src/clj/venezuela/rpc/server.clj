(ns venezuela.rpc.server
  (:import [com.athaydes.protobuf.tcp.api RemoteServices]
           [java.io Closeable]
           [venezuela.rpc HelloService]
           ))


(defn create-server []
  (let [^Closeable server (RemoteServices/provideService
                           (reify HelloService
                             (^String sayHello [_ ^String name]
                              (str "Hello " name))
                             (^String sayHelloAgain [_ ^String name]
                              (str "Hello again, " name)))
                           8081
                           (into-array [HelloService]))]
    (println "Type something to stop the server" )
    (read-line)
    (.close server)))

;;(create-server)


;;(use 'clojure.reflect)

;;(->> (reflect RemoteServices)
;;     :members
;;     (filter #(= (symbol "provideService") (:name %)))
;;
;;     ;;first
;;     ;;:name
;;     ;;type
;;     )


;;(type :hello)
;;(type (symbol "hello"))
;;(type "hello")
;;
;;(reflect HelloService)
;;
