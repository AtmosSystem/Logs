(in-ns 'atmos-logs.core)


(defentity logs
           (pk :id)

           (entity-fields :id :user_entity_id :log_type :date :module :note))


;------------------------------
; BEGIN Logs functions
;------------------------------
(load "logs/logs_datetime")
(load "logs/logs_get")
(load "logs/logs_add")
;------------------------------
; END Logs functions
;------------------------------

(extend-type Map
  ILogRepository
  (add-log [log] (add-logs* log)))

(extend-type Number
  ILogIdentityRepository
  (get-log
    [log-id]
    (get-logs* log-id)))