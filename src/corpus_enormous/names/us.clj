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

(defn female-fnames-seq []
  (->>
   female-first-names-file
   lazy-resource-lines
   (map #(-> % (.split " ") first))))

(defn male-fnames-seq []
  (->>
   male-first-names-file
   lazy-resource-lines
   (map #(-> % (.split " ") first))))

(defn fnames-seq []
  (concat
   (female-fnames-seq)
   (male-fnames-seq)))

(def lnames (memoize #(vec (lnames-seq))))
(def fnames (memoize #(vec (fnames-seq))))

(def female-fnames (memoize #(vec (female-fnames-seq))))
(def male-fnames   (memoize #(vec (male-fnames-seq))))

(defn rand-lname []
  (rand-nth (lnames)))

(defn rand-fname []
  (rand-nth (fnames)))

(defn rand-female-fname []
  (rand-nth (female-fnames)))

(defn rand-male-fname []
  (rand-nth (male-fnames)))


(defn rand-first-last []
  (str (rand-fname) " " (rand-lname)))

(defn rand-last-first []
  (str (rand-lname) ", " (rand-fname)))
