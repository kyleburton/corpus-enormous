(ns corpus-enormous.addresses.us
  (:require
   [corpus-enormous.util     :as util]
   [corpus-enormous.names.us :as names]
   [clojure.tools.logging    :as log]
   [clojure.string           :as string]))

(def zipcodes-resource-file "corpus-enormous/addresses/us/zbp13totals.txt")

(defn random-street-number []
  (util/random-format-number
   (rand-nth ["#" "##" "###"])))

(defn random-directional-prefix []
  (rand-nth ["N." "S." "E." "W." "North" "South" "East" "West"]))

(defn random-street-suffix []
  (rand-nth []))

;; street line 2 (suite, apt)
;; TODO: p.o. boxes

(defonce zip-city-and-state
  (memoize
   (fn []
     (->>
      zipcodes-resource-file
      util/lazy-resource-csv-recs
      (drop 1)
      (mapv
       (fn [[zip5 city-state]]
         (let [[city state] (.split city-state ", " 2)]
           [zip5 city state])))))))

(comment
  (take 10 (zip-city-and-state))

  )

(def street-suffixes
  ["Alley"
   "Avenue"
   "Ave."
   "Boulevard"
   "Blvd."
   "Center"
   "Ctr."
   "Circle"
   "Court"
   "Ct."
   "Crossing"
   "Drive"
   "Dr."
   "Expressway"
   "Freeway"
   "Highway"
   "Hwy"
   "Junction"
   "Lane"
   "Ln"
   "Loop"
   "Oval"
   "Parkway"
   "Pkwy"
   "Pass"
   "Path"
   "Plaza"
   "Point"
   "Road"
   "Rd."
   "Route"
   "Run"
   "Square"
   "Street"
   "St"
   "Throughway"
   "Viaduct"
   "Way"])

(defn random-street-suffix []
  (rand-nth street-suffixes))

(defn random-street-name []
  (names/rand-lname))

(defonce street-number-formats
  ["#"
   "##" "##"
   "###" "###" "###" "###"
   "####" "####"])

(defonce street1-format-fns
  [#(format "%s %s %s"
            (util/random-format-number (rand-nth street-number-formats))
            (string/capitalize
             (random-street-name))
            (random-street-suffix))
   #(format "%s %s %s"
            (util/random-format-number (rand-nth street-number-formats))
            (string/capitalize
             (random-street-name))
            (random-street-suffix))
   #(format "%s %s %s %s"
            (util/random-format-number (rand-nth street-number-formats))
            (random-directional-prefix)
            (string/capitalize
             (random-street-name))
            (random-street-suffix))])

(defn random-street1 []
  (let [fmt (rand-nth street1-format-fns)]
    (fmt)))

(defn random-street2 []
  (let [prob (rand 100)]
    (cond
      (< prob 17)
      (util/random-format-number
       (rand-nth ["Apt #"
                  "Apt ##"
                  "Apt ###"
                  "Unit #"
                  "Unit ##"
                  "Ste #"
                  "Ste ##"
                  "Ste ###"
                  "No. ###"]))
      :otherwise
      nil)))

(defn random-address []
  (let [[zip city state] (rand-nth (zip-city-and-state))
        street1          (random-street1)
        street2          (random-street2)]
    {:street1 street1
     :street2 street2
     :city    city
     :state   state
     :zip     zip}))


