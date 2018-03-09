(ns atmos-logs.api
  (:require [compojure.core :refer [GET POST PUT DELETE defroutes]]
            [atmos-kernel.core :refer [not-found-route
                                       not-implemented-fn
                                       ms-atmos-method
                                       make-json-app
                                       ms-atmos-response
                                       ms-atmos-cond-response
                                       ms-atmos-let-cond-response
                                       ms-atmos-main-method-response
                                       keyword-map
                                       request-body
                                       read-resource-edn]]
            [atmos-rdb-kernel.core :refer [defpersistence init-persistence]]
            [atmos-logs.core :refer :all]
            [clj-time.core :refer [time-zone-for-id]]))

;-------------------------------------------------------
; BEGIN VARS
;-------------------------------------------------------
(def configuration (read-resource-edn :config-prod))

(-> configuration :database defpersistence init-persistence)

;-------------------------------------------------------
; END VARS
;-------------------------------------------------------

;-------------------------------------------------------
; BEGIN Logs functions
;-------------------------------------------------------

(defn- get-logs*
  ([data]
   (ms-atmos-let-cond-response
     [tz (-> configuration :time-zone time-zone-for-id)
      format (:time-zone-format configuration)]
     (nil? data) (get-all-logs tz format)
     (number? data) (get-log data tz format)))
  ([]
   (get-logs* nil)))

(defn- add-logs*
  [data]
  (ms-atmos-let-cond-response
    [log (keyword-map (:log data))
     log (assoc log :date (utc-now))]
    (map? log) (str (add-log log))))


;-------------------------------------------------------
; END Logs functions
;-------------------------------------------------------

(defroutes app-routes
           (ms-atmos-main-method-response :logs)

           (GET
             (ms-atmos-method :logs)
             request
             (get-logs*))

           (GET
             (ms-atmos-method :logs :id)
             [id]
             (get-logs* (Long. id)))

           (PUT
             (ms-atmos-method :logs)
             request
             (add-logs* (request-body request)))

           not-found-route)

(def app (make-json-app app-routes))
