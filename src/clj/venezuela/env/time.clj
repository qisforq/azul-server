(ns venezuela.env.time
  (:require [clj-time.core :as t]))

(defn now []
  (t/now))
