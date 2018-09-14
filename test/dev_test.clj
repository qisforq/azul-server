(ns dev-test
  (:require [clojure.test :refer :all]
            [dev :refer :all]))

(deftest dev-test
  (testing "start! and stop! do not throw"
    (is (not (nil? (start!))))
    (is (not (stop!))))
  (testing "restart! does not throw"
    (is (not (nil? (start!))))
    (is (not (nil? (restart!))))
    (is (not (stop!)))))
