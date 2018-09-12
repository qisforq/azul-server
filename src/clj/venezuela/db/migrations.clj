(ns venezuela.db.migrations
  (:require [migratus.core :as migratus]))

(def config
  {:store :database
   :db {:connection-uri
        "jdbc:postgresql://localhost/azuldb_dev?user=azul&password="}})

(comment
  ;; run the following to run all migrations
  (migratus/migrate config)

  ;; each rollback just goes 1 migration backwards, executed multiple times to
  ;; return to 0
  (migratus/rollback config))


