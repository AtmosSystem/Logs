(ns atmos-logs.api
  (:require [atmos-kernel.web.ring :refer [def-json-web-api]]
            [ring.middleware.defaults :refer [api-defaults]]
            [atmos-kernel.web.security.auth :refer [token-auth]]
            [atmos-kernel.web.route :refer [defatmos-routes
                                            atmos-main-route
                                            atmos-GET
                                            atmos-PUT]]
            [atmos-kernel.serializer.core :refer :all]
            [atmos-logs.core :refer :all]
            [atmos-logs.serializers :refer :all]))


(def logs "logs")
(def log "log")

(declare app app-routes request id)

(defatmos-routes app-routes
                 (atmos-main-route :logs)

                 (atmos-GET [logs logs] request
                            (serialize (get-all logs request) serialize-log))

                 (atmos-PUT [logs log] request
                            (let [log-data (de-serialize (request :params) de-serialize-log)]
                              (add-log log-data))))

(def-json-web-api app app-routes api-defaults token-auth)