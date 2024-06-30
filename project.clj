(defproject corpus-enormous/corpus-enormous "0.1.7"
  :description "Randomized Data Set Generator"
  :url "https://github.com/kyleburton/corpus-enormous"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["releases"  {:url "https://clojars.org/repo" :creds :gpg}]
                        ["snapshots" {:url "https://clojars.org/repo" :creds :gpg}]]
  :profiles {:dev {:resource-paths ["dev-resources"]
                   :dependencies   [[org.clojure/clojure                    "1.11.1"]
                                    [org.clojure/tools.logging              "1.3.0"]
                                    [ch.qos.logback/logback-classic         "1.5.3"]
                                    [ch.qos.logback/logback-core            "1.5.3"]
                                    [nrepl                                  "1.2.0"]
                                    [cider/cider-nrepl                      "0.47.1"]]}}
  :dependencies [[prismatic/schema                       "1.4.1"]
                 [org.clojure/data.csv                   "1.1.0"]])
