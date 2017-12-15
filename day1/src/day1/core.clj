(ns day1.core)

(defn sequense
  "input sequens transformed into int-collection"
  [input-file]
  (->> (slurp input-file)
       (seq)
       (map (fn [char]
              (Character/getNumericValue char)))))

(defn filter-equals
  [seq step]
  (as->(keep-indexed (fn [num item]
                  (if (= (mod num step) 0)
                    item)) seq) $
       (for [i (range 1 (count $))]
         (if (= (nth $ i) (nth $ (- i 1)))
           (nth $ i)
           0
           ))
     ))

(reduce + (filter-equals (concat (sequ) (sequ)) (/ (count (sequ)) 2 )))






















(defn sequ []
  (sequense "/home/jonas/development/advent-of-code-2017/day1/src/day1/sequense"))