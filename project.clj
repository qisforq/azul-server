(defproject venezuela "0.1.0-SNAPSHOT"
  :description "Server implementation for Pale Blue Foundation's Bitcoin wallet"
  :url "http://example.com/FIXME"
  :source-paths      ["src/clj"]
  :java-source-paths ["src/java" "build/generated/source/proto/main"]
  :main venezuela.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clojure.java-time "0.3.2"]

                 ;; RPC
                 [com.google.protobuf/protobuf-java "3.6.1"]

                 ;; GRPC
                 [io.grpc/grpc-core "1.4.0"]
                 [io.grpc/grpc-netty "1.4.0"]
                 [io.grpc/grpc-protobuf "1.4.0"]
                 [io.grpc/grpc-stub "1.4.0"]

                 ;; fixes @javax.annotion.Generated -> cannot find symbol error
                 [javax.annotation/javax.annotation-api "1.2"]

                 ;; SQL
                 [honeysql "0.9.3"]

                 ;; Push
                 [kemurisense/twijio "0.1.1"]
                 [com.google.gcm/gcm-server "1.0.0"]

                 ;; DB
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [migratus "1.0.1"]
                 [com.layerware/hugsql "0.4.9"]

                 ;; Services
                 [integrant "0.6.3"]

                 ;; Logging
                 [com.taoensso/timbre "4.10.0"]
                 
                 ;; Auth
                 [buddy/buddy-hashers "1.3.0"]
                 
              ]

  :plugins [[migratus-lein "0.6.0"]]

  :migratus
  {:store :database
   :migration-dir "migrations"
   :db
   {:connection-uri
    "jdbc:postgresql://localhost/azuldb_dev?user=azul&password="}}

  :profiles
   {:dev
    {:main user
     :source-paths ["src/clj" "dev"]
     :dependencies [[org.clojure/tools.namespace "0.3.0-alpha4"]
                    [im.chit/vinyasa "0.4.7"]
                    [pjstadig/humane-test-output "0.8.3"]]
     :plugins [[com.jakemccrary/lein-test-refresh "0.23.0"]]
     :injections [(require '[vinyasa.inject :as inject])
                  (require 'pjstadig.humane-test-output)
                  (pjstadig.humane-test-output/activate!)
                  (inject/in ;; the default injected namespace is `.`

                   [clojure.pprint pprint]
                   [clojure.repl apropos dir dir-fn doc find-doc pst root-cause source]
                   [clojure.tools.namespace.repl refresh refresh-all]
                   [clojure.java.shell sh])]}})
