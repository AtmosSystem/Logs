(in-ns 'atmos-logs.core)


(defentity logs
           (pk :id)

           (entity-fields :id :user_entity_id :log_type :date :module :note))


(def ^:private date-format "yyyy-MM-dd HH:mm:ss")
(def ^:private joda-date-formatter (formatter date-format))

(defn joda-time-zone-date-formatter
  [format]
  (formatter-local format))

(defn utc-now [] (unparse joda-date-formatter (now)))

(defn convert-time-zone
  [date time-zone format]
  (unparse (joda-time-zone-date-formatter format) (to-time-zone (from-sql-time date) time-zone)))

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
    [log-id time-zone format]
    (map #(assoc % :date
                   (convert-time-zone (:date %) time-zone format))
         (get-logs* log-id))))