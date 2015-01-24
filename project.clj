(defproject hwo2014bot "0.1.0"
  :description "HWO2014 Clojure Bot for my Talk at ClojureD 2015"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [aleph "0.3.2"]
                 [org.clojure/data.json "0.2.3"]
                 [org.clojure/tools.logging "0.2.6"]
                 [log4j "1.2.17"]
                 [incanter "1.5.5"]
                 [expresso "0.2.0"]]
  :main ^:skip-aot hwo2014bot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
