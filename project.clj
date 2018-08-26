(defproject venezuela "0.1.0-SNAPSHOT"
  :description "Server implementation for Pale Blue Foundation's Bitcoin wallet"
  :url "http://example.com/FIXME"
  :source-paths      ["src/clj"]
  :java-source-paths ["src/java"]
  :main venezuela.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-time "0.14.4"]

                 ;; RPC
                 [com.google.protobuf/protobuf-java "3.6.1"]
                 [com.athaydes.protobuf/protobuf-tcp-rpc "0.2.1"]

                 ;; Bitcoin
                 [org.bitcoinj/bitcoinj-core "0.14.7"]
                 [org.lightningj/lightningj "0.4.2-Beta-2"]

                 ;; Persistence
                 [com.taoensso/faraday "1.9.0"]
                 [jstrutz/hashids "1.0.1"]

                 ;; Push
                 [kemurisense/twijio "0.1.1"]
                 [com.google.gcm/gcm-server "1.0.0"]

                 ])


