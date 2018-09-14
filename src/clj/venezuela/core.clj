(ns venezuela.core
  (:gen-class)
  (:require
   [venezuela.rpc.server :refer [start-server]]))

(defn -main
  [& args]
  (let [shutdown-promise (promise)]
    (.addShutdownHook (Runtime/getRuntime)
                      (Thread. (fn [] (deliver shutdown-promise :done))))
    (start-server 8080)
    @shutdown-promise))
