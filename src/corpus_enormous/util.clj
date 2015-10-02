(ns corpus-enormous.util
  (:require
   [clojure.java.io :as io]
   [clojure.string  :as string]
   [clojure.data.csv :as csv]))

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

(defn random-format-number [pat]
  (let [res (string/replace
             pat
             #"#"
             (fn [_]
               (str (rand-int 10))))]
    (.replaceAll res "\\b0+(?=[^0])" "")))

(defn lazy-resource-csv-recs [filename]
  (let [rdr    (io/reader (io/resource filename))
        helper (fn helper [[rec & recs]]
                 (lazy-seq
                  (if rec
                    (cons rec (helper recs))
                    (do
                      (.close rdr)
                      nil))))]
    (helper (csv/read-csv rdr))))


