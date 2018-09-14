(ns venezuela.rpc.server-test
  (:require [clojure.test :refer :all]
            [venezuela.rpc.server :refer :all]))

(deftest rpc-server-test
  (testing "start-server and stop-server do not throw"
    (is (not (nil? (stop-server (start-server 4567)))))))
