(defproject org.clojars.atmos-system/atmos-logs "3.5"
    :description "Log library for atmos system"
    :url "https://github.com/AtmosSystem/Logs"
    :license {:name "Eclipse Public License"
              :url  "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.11.1"]
                   [org.clojars.atmos-system/atmos-kernel "2.2"]]
    :profiles {:dev  {:dependencies [[org.clojure/test.check "0.9.0"]]}}
    :deploy-repositories [["clojars" {:sign-releases false
                                      :url           "https://repo.clojars.org/"
                                      :username      :env/clojars_username
                                      :password      :env/clojars_password}]])
