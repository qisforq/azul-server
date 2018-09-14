(ns venezuela.system
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]
            [venezuela.rpc.server :as server]))

(def config
  {:rpc/server {:port 8080}})

(defmethod ig/init-key :rpc/server [_ {:keys [port] :as opts}]
  (log/debug "Starting RPC server with " opts)
  (server/start-server port))

(defmethod ig/halt-key! :rpc/server [_ server]
  (log/debug "Halting RPC server")
  (server/stop-server server))
