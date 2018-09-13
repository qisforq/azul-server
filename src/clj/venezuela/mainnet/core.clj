;; (ns venezuela.mainnet.core
;;   (:import [com.google.common.util.concurrent
;;             Futures
;;             FutureCallback]
;;            [java.io File]
;;            [org.bitcoinj.kits WalletAppKit]
;;            [org.bitcoinj.wallet.listeners WalletCoinsReceivedEventListener]
;;            [org.bitcoinj.wallet
;;             Wallet
;;             Wallet$SendResult]
;;            [org.bitcoinj.core
;;             Coin
;;             ECKey
;;             NetworkParameters
;;             Transaction
;;             TransactionConfidence]
;;            [org.bitcoinj.params
;;             RegTestParams
;;             MainNetParams
;;             TestNet3Params]))

;; (defn init []
;;   (let [network "testnet"
;;         {^NetworkParameters params :params
;;          file-prefix :filePrefix} (condp network =
;;                                    "testnet" {:params (TestNet3Params/get)
;;                                               :filePrefix "forwarding-service-testnet"}
;;                                    "regtest" {:params (RegTestParams/get)
;;                                               :filePrefix "forwarding-service-regtest"}
;;                                    :else {:params (MainNetParams/get)
;;                                           :filePrefix "forwarding-service"})
;;         ^WalletAppKit kit (proxy [WalletAppKit] [params
;;                                                  (File. ".") file-prefix]
;;                             (onSetupCompleted []
;;                               ;; This is called in a background thread after startAndWait is called, as setting up various objects
;;                               ;; can do disk and network IO that may cause UI jank;stuttering in wallet apps if it were to be done
;;                               ;; on the main thread.
;;                               (when (< (.getKeyChainGroupSize (.wallet this)) 1)
;;                                 (.importKey (.wallet this) (ECKey.)))))]

;;     (when (= params (.get RegTestParams))
;;       ;; Regression test mode is designed for testing and development only, so there's no public network for it.
;;       ;; If you pick this mode, you're expected to be running a local "bitcoind -regtest" instance.
;;       (.connectToLocalHost kit))

;;     ;; Download the block chain and wait until it's done.
;;     (.startAsync kit)
;;     (.awaitRunning kit)))

;; (defn listen-to-transactions
;;   "Spins up a listener for coins received on this wallet"
;;   [kit]
;;   (-> kit
;;       .wallet
;;       (.addCoinsReceivedEventListener
;;        (reify WalletCoinsReceivedEventListener
;;          (^void onCoinsReceived [_ ^Wallet w ^Transaction tx ^Coin prevBalance ^Coin newBalance]
;;            ;; Runs in the dedicated "user thread".
;;            ;;
;;            ;; The transaction "tx" can either be pending, or included into a block (we didn't see the broadcast).
;;            (let [^Coin value (.getValueSentToMe tx w)]
;;              (println "Received tx for " (.toFriendlyString value) ": " tx)
;;              (println "Transaction will be forwarded after it confirms.")
;;              ;; Wait until it's made it into the block chain (may run immediately if it's already there).
;;              ;;
;;              ;; For this dummy app of course, we could just forward the unconfirmed transaction. If it were
;;              ;; to be double spent, no harm done. Wallet.allowSpendingUnconfirmedTransactions() would have to
;;              ;; be called in onSetupCompleted() above. But we don't do that here to demonstrate the more common
;;              ;; case of waiting for a block.
;;              (Futures/addCallback
;;               (-> tx
;;                   (.getConfidence)
;;                   (.getDepthFuture 1))
;;               (reify FutureCallback
;;                 (onSuccess [this result]
;;                   ;; "result" here is the same as "tx" above, but we use it anyway for clarity.
;;                  ;;(forwardCoins result)
;;                  (println "TODO: Forward the coins"))
;;                 (onFailure [this t])))))))))

;; (defn send-money [kit address]
;;   (let [^Coin value Coin/SATOSHI
;;         _ (println "Sending " (.toFriendlyString value) " BTC")
;;         ;; Now send the coins! Send with a small fee attached to ensure rapid confirmation.
;;         ^Coin amountToSend (.subtract value Transaction/REFERENCE_DEFAULT_MIN_TX_FEE)
;;         ^Wallet$SendResult sendResult (-> kit
;;                                           .wallet
;;                                           (.sendCoins
;;                                            (.peerGroup kit)
;;                                            address
;;                                            amountToSend))]
;;     (println "Sending ...")
;;     ;; Register a callback that is invoked when the transaction has propagated across the network.
;;     ;; This shows a second style of registering ListenableFuture callbacks, it works when you don't
;;     ;; need access to the object the future returns.
;;     (-> sendResult
;;         .-broadcastComplete
;;         (.addListener (reify Runnable
;;                         (run [this]
;;                           ;; The wallet has changed now, it'll get auto saved shortly or when the app shuts down.
;;                           (println "Sent coins onwards! Transaction hash is " +
;;                                    (-> sendResult
;;                                        .-tx
;;                                        .getHashAsString))))))))
