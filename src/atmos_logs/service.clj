(ns atmos-logs.service
  (:require [atmos-logs.implementation.core :refer :all]
            [atmos-logs.core :refer :all]))


;-------------------------------------------------------
; BEGIN Logs functions
;-------------------------------------------------------

(defn get-logs
  ([data]
   (cond
     (nil? data) (get-all-logs)
     (number? data) (get-log data)))
  ([]
   (get-logs nil)))

(defn add-logs
  [data]
  (let
    [log (:log data)
     log (assoc log :date (utc-now-string))]
    (cond
      (map? log) (add-log log))))


;-------------------------------------------------------
; END Logs functions
;-------------------------------------------------------