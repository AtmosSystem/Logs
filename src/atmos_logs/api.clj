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
            [atmos-logs.core :refer :all]))

;-------------------------------------------------------
; BEGIN VARS
;-------------------------------------------------------
(def configuration (read-resource-edn :config-prod))

(-> configuration :database defpersistence init-persistence)

;-------------------------------------------------------
; END VARS
;-------------------------------------------------------
