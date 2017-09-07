(ns corpus-enormous.people
  (:require
   [corpus-enormous.addresses.us :as addresses]
   [corpus-enormous.emails       :as emails]
   [corpus-enormous.names.us     :as names]
   [corpus-enormous.phones.us    :as phones]
   [corpus-enormous.util         :as util]
   [clojure.string               :as string]))

(defn rand-ssn []
  (str
   (inc (rand-int 9))
   (string/replace
    "##-##-####"
    #"#"
    (fn [_]
      (str (rand-int 10))))))

(defn random-person []
  (let [gender     (rand-nth ["M" "F"])
        first-name (case gender
                     "M" (names/rand-male-fname)
                     "F" (names/rand-female-fname)
                     (names/rand-female-fname))
        last-name  (names/rand-lname)]
    (merge
     {:first-name first-name
      :last-name  last-name
      :gender     gender
      :email      (emails/rand-email-address first-name last-name)
      :ssn        (rand-ssn)
      :phone      (phones/random-phone-number)}
     (addresses/random-address))))

