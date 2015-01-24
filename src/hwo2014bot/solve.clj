(ns hwo2014bot.solve
  (:require [hwo2014bot.plot :refer [car-data draw-lines]]
            [numeric.expresso.core :as ex]))

;guess formular for position:
(defn solve1 [[pos-2 pos-1 pos] throttle]
  (first (ex/solve 'a
            (ex/ex
             (= ~pos (+ ~pos-1
                        (- ~pos-1 ~pos-2)
                        (* a ~throttle)))))))

(def poss (map (comp :abs-dist :me) car-data))
(def ts (map (comp :data :answer) car-data))
(def r (map solve1 (partition 3 1 (drop 2 poss)) ts))

#_(draw-lines (take 500 r) (take 500 ts))

(defn solve2 [[pos-3 pos-2 pos-1 pos] [throttle-1 throttle]]
  (first (ex/solve '[a b]
            (ex/ex
             (= ~pos (+ ~pos-1
                        (* b (- ~pos-1 ~pos-2))
                        (* a ~throttle))))
                   (ex/ex
             (= ~pos-1 (+ ~pos-2
                        (* b (- ~pos-2 ~pos-3))
                        (* a ~throttle-1)))))))

(def r2 (map solve2 (partition 4 1 (drop 2 poss)) (partition 2 1 ts)))

#_(draw-lines (take 500 (map (comp first vals) r2))
            (take 500 (map (comp second vals) r2)))
