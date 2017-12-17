;--- Day 2: Corruption Checksum ---
;As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears to be idle. Come help us repair the corruption in this spreadsheet - if we take another millisecond, we'll have to display an hourglass cursor!"
;
;The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.
;
;For example, given the following spreadsheet:
;
;5 1 9 5
;7 5 3
;2 4 6 8
;The first row's largest and smallest values are 9 and 1, and their difference is 8.
;The second row's largest and smallest values are 7 and 3, and their difference is 4.
;The third row's difference is 6.
;In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
;
;What is the checksum for the spreadsheet in your puzzle input?
;
;Your puzzle answer was 53460.
;
;The first half of this puzzle is complete! It provides one gold star: *
;
;--- Part Two ---
;"Great work; looks like we're on the right track after all. Here's a star for your effort." However, the program seems a little worried. Can programs be worried?
;
;"Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us specialize in bitwise operations."
;
;It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is, where the result of the division operation is a whole number. They would like you to find those numbers on each line, divide them, and add up each line's result.
;
;For example, given the following spreadsheet:
;
;5 9 2 8
;9 4 7 3
;3 8 6 5
;
;In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
;In the second row, the two numbers are 9 and 3; the result is 3.
;In the third row, the result is 2.
;In this example, the sum of the results would be 4 + 3 + 2 = 9.
;
;What is the sum of each row's result in your puzzle input?

(ns day1.day2
  (:require [clojure.math.combinatorics :as combo])
  (:require [clojure.string :as str]))

(defn read-spreadsheet [path-to-txt-input]
  (->> (str/split (slurp path-to-txt-input) #"\n")
       (map (fn [row]
              (as-> (str/split row #"\t") $
                    (map (fn [number]
                           (Integer/parseInt number)) $))))
       (into [])))


(defn calculate-checksum
  "Calculates the checksum according to the first problem"
  [input-matrix operator-function]
  (->> (map (fn [row]
              (operator-function (apply max row) (apply min row))) input-matrix)
       (reduce +)))


(defn find-divisors
  "Find combinations that divides each other"
  [input-matrix]
  (->> (map (fn [row]
              (->> (combo/combinations row 2)
                   (filter (fn [combination]
                             (or (= (mod (first combination) (last combination)) 0)
                                 (= (mod (last combination) (first combination)) 0))))
                   (flatten)
                   )) input-matrix)))


;; Answer to problem 1
(-> (read-spreadsheet "/home/jonas/development/advent-of-code-2017/day1/src/day1/spreadsheet")
    (calculate-checksum -))

;; Answer to problem 2
(-> (read-spreadsheet "/home/jonas/development/advent-of-code-2017/day1/src/day1/spreadsheet")
    (find-divisors)
    (calculate-checksum /))