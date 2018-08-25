(ns venezuela.lightning.core
  (:import
   [java.io File]
   [java.util Iterator]
   [org.lightningj.lnd.wrapper SynchronousLndAPI]
   [org.lightningj.lnd.wrapper.message OpenChannelRequest]
           ))

(defn init []
  (let [host         "localhost"
        port         10001
        certificate  (File. "/Library/Application Support/Lnd/tls.cert")
        macaron      (File. (str (System/getProperty "user.home") "/Library/Application Support/Lnd/admin.macaroon"))
        ^SynchronousLndAPI sync-api (SynchronousLndAPI. host port certificate macaron)]
    sync-api))

(defn balance [sync-api]
  ;; Example call to get channel balance and write output as JSON (pretty printed)
  (println (-> sync-api
               .channelBalance
               (.toJsonAsString true))))

(defn list-peers [sync-api]
  ;; Calls returns a wrapped response or Iterator of wrapped responses.
  ;; The response can be converted to XML or JSON or just parsed.
  (let [^ListPeersResponse listPeersResponse (.listPeers sync-api)]
    listPeersResponse))

(defn advanced-call [sync-api]
  ;; A more advanced call returning an iterator is for example openChannel().
  (let [
        ;; there are two ways to generate a request.
        ;; Either build up a request object like below:
        ^OpenChannelRequest openChannelRequest (doto (OpenChannelRequest.)
                                                 (.setNodePubkeyString "02ad1fddad0c572ec3e886cbea31bbafa30b5f7e745da7e936ed9d1471116cdc02")
                                                 (.setLocalFundingAmount 40000)
                                                 (.setPushSat 25000)
                                                 (.setSatPerByte 0))

        ;; Perform the call using alternative 1
        ^Iterator result (.openChannel sync-api openChannelRequest)

        ;; This call will wait for a the channel has opened, which means confirmation block must
        ;; generated in btc. If simnet is used you can manually generate blocks with
        ;; 'btcctl --simnet --rpcuser=kek --rpcpass=kek generate 3'
        ]
    (loop []
      (when (.hasNext result)
        (println "Received Update: " (-> result
                                         .next
                                         (.toJsonAsString true)))
        (recur)))))


(defn shutdown! [sync-api]
  ;; To close the api use the method
  (.close sync-api))

(defn send-money []

  )
