(ns corpus-enormous.util-test
  (:require
   [clojure.test :refer :all]
   [corpus-enormous.util :as util]
   [clojure.string :as string]))


(deftest test-make-seq-of
  (is (= [:things :things :things]) (vec (take 3 (util/make-seq-of (fn [] :things))))))
