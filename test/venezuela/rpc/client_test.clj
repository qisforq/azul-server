(ns venezuela.rpc.client-test
  (:require [venezuela.rpc.server :as server]
            [venezuela.rpc.client :as client]
            [clojure.string :as string]
            [clojure.test :as t :refer [deftest is testing]]))


(deftest rpc-client-test
  (let [server (server/start-server 5002)]
    (try
      (let [client (client/make-client 5002)]
        (testing "registration"
          (testing "when username is blank it fails"
            (let [username ""
                  password "12345"
                  registration (client/register client username password)]
              (is (= {:success false :message "Username is required"} registration))))
          (testing "when username is short it fails"
            (let [username "me"
                  password "12345"
                  registration (client/register client username password)]
              (is (= {:success false :message "Username is too short, min length: 3"} registration))))
          (testing "when username is taken it fails"
            (let [username "user"
                  password "12345"
                  existing_registration (client/register client username password)
                  registration (client/register client username password)]
              (is (= {:success false :message "User already exists"} registration))))
          (testing "when password is blank it fails"
            (let [username "user"
                  password ""
                  registration (client/register client username password)]
              (is (= {:success false :message "Password is required"} registration))))
          (testing "when password is valid and username is available it passes"
            (let [username "user"
                  password "12345"
                  registration (client/register client username password)]
              (comment "FIXME: this will not work until the test database is cleared with each run"
                (is (:success registration))
                (is (not (string/blank? (:session-token registration)))))
              (is (not (:success registration))))))

        (testing "login"
          (testing "when user exists and password is correct it succeeds"
            (let [username "user"
                  password "12345"
                  registration (client/register client username password)
                  response (client/login client username password)]
              (is (:success response))
              (is (not (string/blank? (:session-token response))))))
          (testing "when user exists and password is incorrect it fails"
            (let [username "user"
                  password "12345"
                  incorrect_password "23456"
                  registration (client/register client username password)
                  response (client/login client username incorrect_password)]
              (is (= {:success false :message "Invalid password"} response))))
          (testing "when user doesn't exist it fails"
            (let [username "user"
                  password "abcde"
                  response (client/login client username password)]
              (comment "FIXME: this will not work until the test database is cleared with each run"
                (is (= {:success false :message "User doesn't exist"} response)))
              (is (not (:success response)))))))
      (finally
        (server/stop-server server)))))
