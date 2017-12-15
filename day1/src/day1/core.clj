(ns day1.core)


(defn sequense
  "input sequence transformed into int-collection"
  [path-to-file]
  (->> (slurp path-to-file)
       (seq)
       (map (fn [char]
              (Character/getNumericValue char)))))


(defn filter-equals
  "saves equal elements (by step apart), sets unequal elements to 0"
  [sequ step]
  (let [second-iterator (fn [index]
                          (mod (+ index step) (count sequ)))]
    (map-indexed (fn [first-iterator item]
                   (if (= item (nth sequ (second-iterator first-iterator)))
                     item
                     0)) sequ)))


;; Answer 1
(as-> (sequense "/home/jonas/development/advent-of-code-2017/day1/src/day1/sequense") $
    (filter-equals $ 1)
    (reduce + $))

;;Answer 2
(as-> (sequense "/home/jonas/development/advent-of-code-2017/day1/src/day1/sequense") $
      (filter-equals $ (/ (count $) 2))
      (reduce + $))