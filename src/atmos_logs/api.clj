(ns atmos-logs.api
  (:require [atmos-kernel.web.core :refer [json-web-app
                                           request-body]]
            [atmos-kernel.web.security.auth :refer [token-auth]]
            [atmos-kernel.web.route :refer [defatmos-route
                                            atmos-GET
                                            atmos-PUT]]
            [atmos-logs.core :refer :all]))


(def logs "logs")
(def log "log")

(defatmos-route app-routes :logs
                (atmos-GET [logs] (get-all logs)
                           :authentication-needed? true)

                (atmos-GET [log :id] (get-log (Long. (str id)))
                           :authentication-needed? true)

                (atmos-PUT [log] (add-log (request-body request))
                           :authentication-needed? true))

(def app (json-web-app app-routes token-auth))