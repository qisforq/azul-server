(ns venezuela.auth.login-test
  (:require [clojure.test :refer [deftest testing is]]
            [venezuela.db.persistence :as db]
            [venezuela.auth.login :as login]
            ))


(deftest login-test
  (testing "create"
    (testing "Can login to newly created user"
      (login/create "TestUser" "TestPass")
      (is (:success (login/login "TestUser" "TestPass"))))))
