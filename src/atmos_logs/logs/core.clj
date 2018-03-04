(in-ns 'atmos-logs.core)


(defentity logs
           (pk :id)

           (entity-fields :id :user_id :log_type :date :module :note))

;------------------------------
; BEGIN Logs functions
;------------------------------
(load "logs/logs_get")
(load "logs/logs_add")
;------------------------------
; END Logs functions
;------------------------------

(def ^:private date-format "yyyy-MM-dd hh:mm:ss")

(defn utc-now
  []
  (let [formatter (formatter date-format)]
    (unparse formatter (now))))

(extend-type PersistentArrayMap
  ILogRepository
  (add-log [log] (add-logs* log)))

(extend-type Number
  ILogIdentityRepository
  (get-log [log-id] (get-logs* log-id)))