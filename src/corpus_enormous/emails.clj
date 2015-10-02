(ns corpus-enormous.emails
  (:require
   [corpus-enormous.util     :refer [lazy-resource-lines] :as util]
   [corpus-enormous.names.us :as names]))

(def tlds-resource-file "corpus-enormous/internet/tlds.txt")

(def tlds
  (memoize
   (fn []
     (->>
      tlds-resource-file
      lazy-resource-lines
      (filterv #(.matches % "\\.[a-z]{2}.+"))))))

(defn rand-tld []
  (rand-nth (tlds)))

(defn rand-common-tld []
  (rand-nth [".com" ".com" ".com" ".com" ".com" ".com" ".com" ".com" ".com"
             ".org" ".org" ".org" ".org"
             ".edu" ".edu" ".edu"
             ".gov" ".gov"
             ".uk"
             ".net"
             ".co.uk"
             ".ca"
             ".de"
             ".jp"
             ".fr"
             ".au"
             ".us"]))

(def domains-list-resource-file "corpus-enormous/internet/domains.txt")

(def domains
  (memoize
   (fn []
     (->>
      domains-list-resource-file
      lazy-resource-lines))))

(defn rand-domain []
  (rand-nth (domains)))

(comment
  (->>
   rand-domain
   repeatedly
   (take 100))

  )



(def email-formats
  [(fn [[fname lname]] (format "%s.%s@%s"
                               (.toLowerCase (or fname (names/rand-fname)))
                               (.toLowerCase (or lname (names/rand-lname)))
                               (rand-domain)))
   (fn [[fname lname]] (format "%s%s%s@%s"
                               (str (.charAt (.toLowerCase (or fname (names/rand-fname))) 0))
                               (.toLowerCase (or lname (names/rand-lname)))
                               (util/random-format-number (rand-nth ["#" "##" "##"]))
                               (rand-domain)))
   (fn [[fname lname]] (format "%s%s%s@%s"
                               (.toLowerCase (or fname (names/rand-fname)))
                               (str (.charAt (.toLowerCase (or lname (names/rand-lname))) 0))
                               (util/random-format-number (rand-nth ["##" "##"]))
                               (rand-domain)))
   (fn [[fname lname]] (format "%s.%s%s@%s"
                               (str (.charAt (.toLowerCase (or fname (names/rand-fname))) 0))
                               (.toLowerCase (or lname (names/rand-lname)))
                               (util/random-format-number (rand-nth ["##" "##"]))
                               (rand-domain)))])

(defn rand-email-address
  ([]
   ((rand-nth email-formats) []))
  ([fname lname]
   ((rand-nth email-formats) [fname lname])))

(comment
  (->>
   rand-email-address
   repeatedly
   (take 100))
  )
