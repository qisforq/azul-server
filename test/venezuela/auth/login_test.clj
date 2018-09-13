(ns venezuela.auth.login-test
  (:require [clojure.test :refer [deftest testing is]]
            [venezuela.db.persistence :as db]
            [venezuela.auth.login :as l]
            ))


(deftest create-test
  (testing "Can login to newly created user"
    (l/create "TestUser" "TestPass")
    (is (:success (l/login "TestUser" "TestPass")))))
