(ns corpus-enormous.core
  (:require
   [corpus-enormous.emails   :as emails]
   [corpus-enormous.names.us :as us-names]
   [clojure.tools.logging    :as log]))

(defn -main [& args]
  (let [cmd (-> args first (or "help"))]))
