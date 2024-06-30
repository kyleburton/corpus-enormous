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

(defn make-seq-shift-fn [s]
  (let [s (atom s)]
    (fn take-next-from-seq []
      (let [[hd & tail] @s]
        (reset! s tail)
        hd))))

(defn make-seq-of [f]
  (let [ff (fn inner-fn [] (lazy-seq (cons (f) (inner-fn))))]
    (ff)))

(defn format-number [^String pat digits]
  (let [next-digit (make-seq-shift-fn digits)
        res        (string/replace
                    pat
                    #"#"
                    (fn [_]
                      (str (next-digit))))]
    res
    #_(.replaceAll res "\\b0+(?=[^0])" "")))

(defn random-format-number [pat]
  (format-number pat (make-seq-of #(rand-int 10))))

(defn slurp-resource [filename]
  (slurp (io/resource filename)))

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
