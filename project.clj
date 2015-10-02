(defproject corpus-enormous "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies   [
                                     [org.clojure/tools.logging              "0.3.1"]
                                     [ch.qos.logback/logback-classic         "1.0.13"]
                                     [org.clojure/tools.nrepl                "0.2.11"]
                                     ;; NB: cider-nrepl is up to 0.9.1
                                     [cider/cider-nrepl                      "0.7.0"]
                                     [prismatic/schema                       "1.0.1"]
                                     [org.clojure/data.csv                   "0.1.3"]
                                    ]}}
  :dependencies [[org.clojure/clojure "1.7.0"]])
