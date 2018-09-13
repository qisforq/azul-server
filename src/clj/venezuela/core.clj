(ns venezuela.core
  (:require
   [venezuela.rpc.server :refer [start-server]]))

(defn -main []
  (start-server))
