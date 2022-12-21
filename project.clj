(defproject atmos-logs "3.0-SNAPSHOT"
    :description "Logs micro service for atmos system"
    :url "https://github.com/AtmosSystem/Logs"
    :license {:name "Eclipse Public License"
              :url  "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.11.1"]
                   [atmos-kernel "2.2-SNAPSHOT"]
                   [atmos-web-kernel-reitit "1.0-SNAPSHOT"]
                   [metosin/reitit-middleware "0.5.18"]
                   [metosin/muuntaja "0.6.8"]
                   [camel-snake-kebab "0.4.3"]]
    :plugins [[lein-ring "0.12.6"]]
    :ring {:handler atmos-logs.web.api/app}
    :repositories [["releases" {:url           "https://clojars.org/repo"
                                :username      :env/CLOJAR_USERNAME
                                :password      :env/CLOJAR_PASSWORD
                                :sign-releases false}]])
