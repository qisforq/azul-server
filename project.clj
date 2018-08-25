(defproject venezuela "0.1.0-SNAPSHOT"
  :description "Server implementation for Pale Blue Foundation's Bitcoin wallet"
  :url "http://example.com/FIXME"
  :source-paths      ["src/clj"]
  :java-source-paths ["src/java"]
  :main venezuela.core
  :dependencies [[org.clojure/clojure "1.8.0"]

                 [com.google.protobuf/protobuf-java "3.6.1"]

                 [org.bitcoinj/bitcoinj-core "0.14.7"]
                 [org.lightningj/lightningj "0.4.2-Beta-2"]

                 [com.athaydes.protobuf/protobuf-tcp-rpc "0.2.1"]

                 [com.taoensso/faraday "1.9.0"]
                 [kemurisense/twijio "0.1.1"]
                 [jstrutz/hashids "1.0.1"]
                 [clj-time "0.14.4"]
                 [danlentz/clj-uuid "0.1.7"]
                 ])


