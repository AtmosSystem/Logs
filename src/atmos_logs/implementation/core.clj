(ns atmos-logs.implementation.core
  (:require [atmos-logs.core :refer :all]
            [environ.core :refer [env]]
            [atmos-kernel.configuration :refer [read-edn]]
            [atmos-data-kernel.persistence.core :refer [defpersistence]]
            [korma.core :refer :all]
            [korma.db :refer [defdb]]
            [atmos-data-kernel.persistence.sql :refer [defget-all-entity
                                                       defget-identity-entity
                                                       defadd-entity
                                                       defupdate-entity]]
            [clj-time.core :refer [now to-time-zone]]
            [clj-time.coerce :refer [from-sql-time]]
            [clj-time.format :refer [formatter formatter-local unparse]])
  (:import (java.util Map)))


;-------------------------------------------------------
; BEGIN VARS
;-------------------------------------------------------
(declare atmos-logs)

(def ^:private persistence-type :mysql)

(def ^:private resource-file (or (keyword (env :resource-file)) :config-prod))
(def ^:private configuration (read-edn resource-file))

(def ^:private date-format "yyyy-MM-dd HH:mm:ss")
(def ^:private joda-date-formatter (formatter date-format))
;-------------------------------------------------------
; END VARS
;-------------------------------------------------------

; Persistence initialization
(->> configuration :database (defpersistence persistence-type) (defdb atmos-logs))


(defentity logs
           (pk :id)

           (entity-fields :id :user_entity_id :log_type :date :module :note))


;------------------------------
; BEGIN Logs functions
;------------------------------
(load "logs_datetime")
(load "logs_get")
(load "logs_add")
;------------------------------
; END Logs functions
;------------------------------

(extend-protocol ILogProtocol
  Map
  (add-log [log] (add-logs* log))
  Number
  (get-log [log-id] (get-first-logs* log-id)))
