(ns hwo2014bot.keimola)

;Some things I need to make the calculation of the Positions short

(def keimola
  [{:length 100.0}
   {:length 100.0}
   {:length 100.0}
   {:length 100.0}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 82.46680715673207}
   {:length 100.0}
   {:length 100.0}
   {:length 74.61282552275759}
   {:length 100.0}
   {:length 100.0, :switch true}
   {:length 70.68583470577035}
   {:length 70.68583470577035}
   {:length 70.68583470577035}
   {:length 70.68583470577035}
   {:length 100.0, :switch true}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 82.46680715673207}
   {:length 74.61282552275759}
   {:length 100.0, :switch true}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 62.0}
   {:length 70.68583470577035}
   {:length 70.68583470577035}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 86.39379797371932}
   {:length 100.0, :switch true}
   {:length 100.0}
   {:length 100.0}
   {:length 100.0}
   {:length 90.0}])

(def dists (into [] (map :length keimola)))
(def lap-dist (reduce + dists))

(defn abs-dist [lap piece in-piece]
  (+ (* lap lap-dist)
     (reduce + (take piece dists))
     in-piece))
