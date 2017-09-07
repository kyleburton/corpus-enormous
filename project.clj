(defproject corpus-enormous "0.1.5"
  :description "Randomized Data Set Generator"
  :url "https://github.com/kyleburton/corpus-enormous"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [
                 ["releases"  {:url "https://clojars.org/repo" :creds :gpg}]
                 ["snapshots" {:url "https://clojars.org/repo" :creds :gpg}]]
  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies   [
                                    [org.clojure/tools.logging              "0.3.1"]
                                    [ch.qos.logback/logback-classic         "1.0.13"]
                                    [org.clojure/tools.nrepl                "0.2.12"]
                                    [cider/cider-nrepl                      "0.13.0"]
                                    ]}}
  :dependencies [[org.clojure/clojure                    "1.8.0"]
                 [prismatic/schema                       "1.1.6"]
                 [org.clojure/data.csv                   "0.1.4"]
                 ])
