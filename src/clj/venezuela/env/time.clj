(ns venezuela.env.time
  (:require [java-time :as t]))

(defn now []
  (t/instant))
