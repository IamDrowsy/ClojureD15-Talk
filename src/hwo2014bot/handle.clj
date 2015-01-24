(ns hwo2014bot.handle
  (:require [hwo2014bot.keimola :as k]))

(defn extract-me [data]
  (assoc data :me (get-in data [:input :data 0])))

(defn add-abs-dist [data]
  (assoc-in data [:me :abs-dist]
            (k/abs-dist (get-in data [:me :piecePosition :lap])
                        (get-in data [:me :piecePosition :pieceIndex])
                        (get-in data [:me :piecePosition :inPieceDistance]))))

(defn suggest-throttle-on-angle [data]
  (let [abs-angle (Math/abs (get-in data [:me :angle]))]
    (assoc-in data [:suggest :on-angle]
              {:throttle (if (< abs-angle 10.0)
                           1.0
                           0.0)})))

(defn answer [data]
  (assoc data :answer
    {:msgType "throttle"
     :data (get-in data [:suggest :on-angle :throttle])}))

(defn handle [msg]
  (-> msg
      extract-me
      add-abs-dist
      suggest-throttle-on-angle
      answer))
