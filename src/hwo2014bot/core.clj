(ns hwo2014bot.core
  (:require [clojure.data.json :as json]
            [hwo2014bot.config :as c]
            [hwo2014bot.handle :as h])
  (:use [aleph.tcp :only [tcp-client]]
        [lamina.core :only [enqueue wait-for-result wait-for-message channel close closed?]]
        [gloss.core :only [string]])
  (:gen-class))

(defn- json->clj [string]
  (json/read-str string :key-fn keyword))

(defn send-message [channel message]
  (enqueue channel (json/write-str message)))

(defn read-message [channel]
  (json->clj
    (try
      (wait-for-message channel)
      (catch Exception e
        (println (str "ERROR: " (.getMessage e)))
        #_(System/exit 1)))))

(defn connect-client-channel [host port]
  (wait-for-result
   (tcp-client {:host host,
                :port port,
                :frame (string :utf-8 :delimiters ["\n"])})))

(defmulti handle-msg (comp :msgType :input))

(defmethod handle-msg "carPositions" [msg]
  (h/handle msg))

(defmethod handle-msg :default [msg]
  (merge msg
         {:answer {:msgType "ping" :data "ping"}}))

(defn log-msg [msg]
  (case (:msgType (:input msg))
    "join" (println "Joined")
    "gameStart" (println "Race started")
    "crash" (println "Someone crashed")
    "gameEnd" (println "Race ended")
    "error" (println (str "ERROR: " (:data msg)))
    :noop))

(def data-atom (atom []))

(defn game-loop [channel]
  (let [msg {:input (read-message channel)}]
    (log-msg msg)
    (let [data (handle-msg msg)]
      (swap! data-atom conj data)
      (send-message channel (:answer data))
      (recur channel))))

(defn -main[& [host port botname botkey]]
  (let [channel (connect-client-channel host (Integer/parseInt port))]
    (send-message channel {:msgType "join"
                           :data {:name botname :key botkey}})
    (game-loop channel)))

(defn start-race []
  (let [t (Thread. (partial -main "testserver.helloworldopen.com"
                            "8091" "Drowsy" c/botkey))]
    (.start t)
    t))

#_(def t (start-race))
#_(.stop t)
