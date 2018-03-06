(in-ns 'atmos-logs.core)


(defentity logs
           (pk :id)

           (entity-fields :id :user_entity_id :log_type :date :module :note))


(def ^:private date-format "yyyy-MM-dd hh:mm:ss")
(def ^:private joda-date-formatter (formatter date-format))

(defn utc-now [] (unparse joda-date-formatter (now)))

(defn convert-time-zone
  [date time-zone]
  (unparse joda-date-formatter (to-time-zone (from-sql-time date) time-zone)))

;------------------------------
; BEGIN Logs functions
;------------------------------
(load "logs/logs_get")
(load "logs/logs_add")
;------------------------------
; END Logs functions
;------------------------------

(extend-type PersistentArrayMap
  ILogRepository
  (add-log [log] (add-logs* log)))

(extend-type Number
  ILogIdentityRepository
  (get-log
    [log-id time-zone]
    (map #(assoc % :date
                   (convert-time-zone (:date %) time-zone))
         (get-logs* log-id))))