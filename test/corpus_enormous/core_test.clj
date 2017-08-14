(ns corpus-enormous.core-test
  (:require [clojure.test :refer :all]
            [corpus-enormous.phones.us :as phone]))

(deftest test-phones
  (dotimes [ii 100]
    (is (not (nil? (phone/random-area-code)))))
  (dotimes [ii 100]
   (is (not (nil? (phone/random-us-phone-number))))))
