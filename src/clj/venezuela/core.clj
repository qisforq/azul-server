(ns venezuela.core
  (:require
   [venezuela.rpc.server :refer [create-server]]
  ))

(defn foo [x] (println x "Hello, World!"))


(defn -main []
  (create-server))
