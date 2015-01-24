(ns hwo2014bot.plot
  (:require [incanter.core :as icore]
            [incanter.charts :as icharts]
            [hwo2014bot.core :refer [data-atom]]))

(def car-data (filter #(= "carPositions"
                          (:msgType (:input %)))
                      @data-atom))

(defn draw-lines [x y]
  (let [p (icharts/xy-plot)]
    (icharts/add-lines p (range (count x)) x)
    (icharts/add-lines p (range (count y)) y)
    (icore/view p)))

(def angles (map (comp :angle :me) car-data))
(def throttles (map (comp :data :answer) car-data))

#_(draw-lines angles throttles)

