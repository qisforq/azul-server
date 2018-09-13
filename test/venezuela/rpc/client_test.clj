(ns venezuela.rpc.client-test
  (:require [venezuela.rpc.server :as server]
            [venezuela.rpc.client :as client]
            [clojure.test :as t :refer [deftest is testing]]))


(deftest login-test
  (let [server (server/start-server 5001)]
    (try
      (let [client (client/make-client 5001)]
        (testing "successful path"
          (let [username "me"
                password "12345"
                response (client/login client username password)]
            (is (= {:success true :session-token "12345"} response))))
        (testing "failure path"
          (let [username "me"
                password "abcde"
                response (client/login client username password)]
            (is (= {:success false :message "bad password"} response)))))
      (finally
        (server/stop-server server)))))
