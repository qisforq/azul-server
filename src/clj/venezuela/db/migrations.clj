(ns venezuela.db.migrations
  (:require [migratus.core :as migratus]))

(def config
  {:store :database
   :db {:connection-uri
        "jdbc:postgresql://localhost/azuldb?user=azul&password="}}) 

(comment
  (migratus/migrate config)
  (migratus/rollback config))


