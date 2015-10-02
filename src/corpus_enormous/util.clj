(ns corpus-enormous.util
  (:require
   [clojure.java.io :as io]))

;; From: http://stackoverflow.com/questions/15996035/clojure-way-of-reading-large-files-and-transforming-data-therein
(defn lazy-file-lines
  "open a (probably large) file and make it a available as a lazy seq of lines"
  [filename]
  (letfn [(helper [rdr]
            (lazy-seq
             (if-let [line (.readLine rdr)]
               (cons line (helper rdr))
               (do (.close rdr) nil))))]
    (helper (io/reader filename))))

(defn lazy-resource-lines
  "open a (probably large) file and make it a available as a lazy seq of lines"
  [filename]
  (letfn [(helper [rdr]
            (lazy-seq
             (if-let [line (.readLine rdr)]
               (cons line (helper rdr))
               (do (.close rdr) nil))))]
    (helper (io/reader (io/resource filename)))))


