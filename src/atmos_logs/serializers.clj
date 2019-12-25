(ns atmos-logs.serializers
  (:require [atmos-kernel.serializer.core :refer :all]))

(defrecord Log [id date-time log-type module user data])
(defrecord LogAddition [logger-type log-type module user data])

(defn serialize-log
  [log-data]
  (let [{:keys [id date_time log_type module user data]} log-data

        log-data (mapping (make-fields ->Log serializer-field [[id 'id]
                                                               [date_time 'date_time]
                                                               [log_type 'log_type]
                                                               [module 'module]
                                                               [user 'user]
                                                               [data 'data]]))]

    log-data))

(defn de-serialize-log
  [log-data]
  (mapping (->LogAddition (de-serializer-field (get log-data "logger_type"))
                          (de-serializer-field (get log-data "log_type"))
                          (de-serializer-field (get log-data "module"))
                          (de-serializer-field (get log-data "user" nil) :number)
                          (de-serializer-field (get log-data "data")))))