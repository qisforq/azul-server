(ns venezuela.env.rand
  (:import [java.util UUID]))

;; using repeatedly instead of repeat generates new values,
;; instead of reusing the initial value

(defn uuid []
  (UUID/randomUUID))
