(ns corpus-enormous.core-test
  (:require
   [clojure.test :refer :all]
   [corpus-enormous.phones.us :as phone]
   [clojure.string :as string]))

(defn make-non-rand-nth [start-at]
  (let [itr          (atom start-at)]
    (fn non-rand-nth [s]
      (let [idx @itr]
        (swap! itr inc)
        (nth s (mod idx (count s)))))))

(defn phone->digits [s]
  (let [num (->
             s
             (string/replace #"^1" "")
             (string/replace #"\D" ""))]
    (if (> (count num) 10)
      (.substring num 0 10)
      num)))

(defn valid-us-phone-number? [s]
  ;; strip leading 1 (if present)
  ;; strip all non-digits
  ;; first 3 digits are the AC, must be a valid ac
  ;; 4th char must be 2-9
  (let [s                      (phone->digits s)
        [ac1 ac2 ac3 nxx1 & _] s
        nxx1                   (str nxx1)
        ac                     (str ac1 ac2 ac3)]
    (if (and
         (phone/area-code-detail ac)
         (#{"2" "3" "4" "5" "6" "7" "8" "9"} nxx1))
      true
      false)))

(deftest test-phones
  (let [non-rand-nth (make-non-rand-nth 0)]
    (is (= ["201" "202" "203"]
           [(phone/random-area-code non-rand-nth)
            (phone/random-area-code non-rand-nth)
            (phone/random-area-code non-rand-nth)])))
  
  (let [non-rand-nth (make-non-rand-nth 0)]
    (dotimes [ii 1000]
      (is (phone/area-code-detail (phone/random-area-code non-rand-nth)))))

  (dotimes [ii 10000]
    (is (valid-us-phone-number? (phone/random-phone-number))))
  
  (let [random-area-code  (let [rand-nth (make-non-rand-nth 0)]
                            (fn []
                              (rand-nth phone/us-area-codes)))
        random-nxx-xxx    (let [ctr (atom 0)
                                next-int (fn [_]
                                           (let [ii @ctr]
                                             (swap! ctr inc)
                                             (mod ii 10)))]
                            (fn []
                              [(phone/call-until #(next-int 10) #(> % 1))
                               (next-int 10)
                               (next-int 10)
                               
                               (next-int 10)
                               (next-int 10)
                               (next-int 10)
                               (next-int 10)

                               (next-int 10)
                               (next-int 10)
                               (next-int 10)
                               (next-int 10)]))]
    (is (=
         ["2012345678"
          "2023456789"
          "2034567890"
          "2045678901"
          "2056789012"
          "2067890123"
          "2078901234"
          "2089012345"
          "2092345678"
          "2103456789"]
         (->>
          (range 10)
          (mapv (fn [_]
                  (phone/random-phone-number random-area-code random-nxx-xxx)))
          (mapv phone->digits))))))

