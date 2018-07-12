(ns atmos-logs.api
  (:require [atmos-kernel.web.core :refer [json-web-app]]
            [atmos-kernel.web.core :refer [request-body]]
            [atmos-kernel.web.route :refer [defatmos-route
                                            atmos-GET
                                            atmos-PUT]]
            [atmos-logs.service :refer :all]))


(def logs "logs")
(def log "log")

(defatmos-route app-routes :logs
                (atmos-GET [logs] (get-logs))
                (atmos-GET [log :id] (get-logs (Long. (str id))))
                (atmos-PUT [log] (add-logs (request-body request))))

(def app (json-web-app app-routes))
