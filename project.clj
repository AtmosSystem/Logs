(defproject org.clojars.atmos-system/atmos-logs "3.0"
    :description "Logs micro service for atmos system"
    :url "https://github.com/AtmosSystem/Logs"
    :license {:name "Eclipse Public License"
              :url  "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.11.1"]
                   [org.clojars.atmos-system/atmos-kernel "2.2"]
                   [org.clojars.atmos-system/atmos-web-kernel-reitit "2.0"]]
    :deploy-repositories [["clojars" {:sign-releases false
                                      :url           "https://repo.clojars.org/"
                                      :username      :env/clojars_username
                                      :password      :env/clojars_password}]])
