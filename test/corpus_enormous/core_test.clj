(ns corpus-enormous.core-test
  (:require [clojure.test :refer :all]
            [corpus-enormous.phones.us :as phone]))

(defn make-non-rand-nth [start-at]
  (let [itr          (atom start-at)]
    (fn non-rand-nth [s]
      (let [idx @itr]
        (swap! itr inc)
        (nth s idx)))))

(deftest test-phones
  (let [itr          (atom 0)
        non-rand-nth (make-non-rand-nth 0)]
    (is (= ["201" "202" "203"]
           [(phone/random-area-code non-rand-nth)
            (phone/random-area-code non-rand-nth)
            (phone/random-area-code non-rand-nth)])))
  (dotimes [ii 100]
    (is (not (nil? (phone/random-area-code)))))
  (dotimes [ii 100]
    (is (not (nil? (phone/random-us-phone-number))))))
