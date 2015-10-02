(ns corpus-enormous.names.us
  (:require
   [corpus-enormous.util :refer [lazy-resource-lines]]))

(def female-first-names-file "corpus-enormous/names/us/female-first-names.txt")
(def male-first-names-file   "corpus-enormous/names/us/male-first-names.txt")
(def last-names-file         "corpus-enormous/names/us/last-names.txt")

(defn lnames-seq []
  (->>
   last-names-file
   lazy-resource-lines
   (map #(-> % (.split " ") first))))

(defn fnames-seq []
  (concat
   (->>
    female-first-names-file
    lazy-resource-lines
    (map #(-> % (.split " ") first)))
   (->>
    male-first-names-file
    lazy-resource-lines
    (map #(-> % (.split " ") first)))))

(def lnames (memoize #(vec (lnames-seq))))
(def fnames (memoize #(vec (fnames-seq))))

(defn rand-lname []
  (rand-nth (lnames)))

(defn rand-fname []
  (rand-nth (fnames)))


(defn rand-first-last []
  (str (rand-fname) " " (rand-lname)))

(defn rand-last-first []
  (str (rand-lname) ", " (rand-fname)))
