(ns day1.core)

(defn sequense
  "input sequens transformed into int-collection"
  [path-to-file]
  (->> (slurp path-to-file)
       (seq)
       (map (fn [char]
              (Character/getNumericValue char)))))

(defn filter-equals
  [sequ step]
  (let [second-iterator (fn [index]
                          (mod (+ index step) (count sequ)))]
    (map-indexed (fn [index item]
                   (if (= item (nth sequ (second-iterator index)))
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