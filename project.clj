(defproject venezuela "0.1.0-SNAPSHOT"
  :description "Server implementation for Pale Blue Foundation's Bitcoin wallet"
  :url "http://example.com/FIXME"
  :source-paths      ["src/clj"]
  :java-source-paths ["src/java"]
  :main venezuela.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clojure.java-time "0.3.2"]

                 ;; RPC
                 [com.google.protobuf/protobuf-java "3.6.1"]

                 ;; GRPC
                 [io.grpc/grpc-netty "1.4.0"]
                 [io.grpc/grpc-protobuf "1.4.0"]
                 [io.grpc/grpc-stub "1.4.0"]

                 ;; SQL
                 [honeysql "0.9.3"]

                 ;; Bitcoin
                 [org.bitcoinj/bitcoinj-core "0.14.7"]
                 [org.lightningj/lightningj "0.4.2-Beta-2"]

                 ;; Push
                 [kemurisense/twijio "0.1.1"]
                 [com.google.gcm/gcm-server "1.0.0"]

                 ;; Services
                 [integrant "0.6.3"]

                 ;; Logging
                 [com.taoensso/timbre "4.10.0"]

                 ;; DB Migrations
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [migratus "1.0.1"]])


