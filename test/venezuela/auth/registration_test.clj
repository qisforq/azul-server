(ns venezuela.auth.registration-test
  (:require [clojure.test :refer [deftest testing is]]
            [venezuela.db.persistence :as db]
            [venezuela.auth.registration :as registration]
            [venezuela.auth.login :as login]))

(deftest registration-test
  (testing "create"
    (testing "Can login to newly created user"
      (registration/register "TestUser" "TestPass")
      (is (:success (login/login "TestUser" "TestPass"))))))
