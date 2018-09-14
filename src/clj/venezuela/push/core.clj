(ns venezuela.push.core
  (:require [aleph.http :as http]
            [byte-streams :as bs]
            [clojure.java.io :as io]
            [cheshire.core :as json]))

(def push-url "https://fcm.googleapis.com/fcm/send")
(def test-url "http://requestbin.net/r/xc0vf9xc")

(defonce big-key (-> (io/resource "keys/gcp.json")
                     slurp
                     json/parse-string
                     (get "private_key")))

(def gcp-private-key "AIzaSyC3ZQNoGtl3pijENgvvFk5eymdTPh6ySiA")
(def web-api-key "AIzaSyAHZ76qANEEoju70-j8B-Hl2AF7It5lsZI")
(def legacy-server-key "AIzaSyA6fJsSmwsiOVEnqkqnvhLp66knYOepqqQ")
(def private-key legacy-server-key)

;; Get Android Token
(def device-push-token "ebMF9pscwQ0:APA91bFTmVt8hZ05XQ2I0Bxor8oTzVkQKvrWS5ymd191w-1hN7-nJAXv2mvVz_iR1tsTKPGtfmrgp5zomvHDCTh3EDfBur9-Yg8l-ffz-xR4sciDc343tZAaixsNM0q_hCSlpjoWZ_jQ")

(def headers {"Content-Type" "application/json"
              "Authorization" (format "key=%s" private-key)})

(def example-message
  {:data {:score "5x1"
          :time "15:10"}
   :notification {:body "This is a Firebase Cloud Messaging Message!"
                  :title "Azul is the bomb"}
   :to device-push-token})

(defn hello-http []
  (-> @(http/get "https://google.com/")
      :body
      bs/to-string
      prn))

(defn send-push []
  (try @(http/post
         push-url
         ;;ktest-url
         {:headers headers
          :body (json/generate-string example-message)})
       (catch Exception e
         (-> (ex-data e)
             :body
             bs/to-string
             ))))
