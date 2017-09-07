(ns corpus-enormous.people-test
  (:require
   [clojure.test :refer :all]
   [corpus-enormous.people :as people]
   [clojure.string :as string]))

(deftest test-gen-person
  (is (not (nil? (people/random-person)))))
