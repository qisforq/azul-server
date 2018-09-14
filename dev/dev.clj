(ns dev
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.pprint :refer (pprint)]
            [clojure.repl :refer :all]
            [clojure.test :as test]
            [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [integrant.core :as ig]
            [taoensso.timbre :as log]

            [venezuela.system] ;; ig multimethods
            ))

(def config
  {:migrations/config {:store :database
                     :db {:connection-uri
                           "jdbc:postgresql://localhost/azuldb_dev?user=azul&password="}}})

(def integrant_config
  {:rpc/server {:port 5001}})


;; var to store running system
(def system nil)

(defn start!
  []
  (log/info "Starting dev system with config:" integrant_config)
  (alter-var-root #'system (fn [_] (ig/init integrant_config)))
  (log/info "Started dev system")
  system)


(defn stop!
  []
  (log/info "Stopping system")
  (if system
    (alter-var-root #'system ig/halt!))
  (log/info "Stopped system")
  system)


(defn restart!
  []
  (log/info "Restarting system")
  (stop!)
  (refresh :after 'dev/start!))
