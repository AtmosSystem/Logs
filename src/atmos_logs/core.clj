(ns atmos-logs.core)

(def log-types [::info ::debug ::error ::exception ::fatal])

(defprotocol IPersistenceLogger
    "Protocol to persist log data"
    (add-log [log]))

(defprotocol ILoggerActions
    (info [data])
    (debug [data])
    (error [data])
    (exception [data])
    (fatal [data]))
