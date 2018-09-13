(ns venezuela.core
  (:require
   [venezuela.rpc.server :refer [start-server]]
  ))

(defn foo [x] (println x "Hello, World!"))


(defn -main []
  (start-server))
