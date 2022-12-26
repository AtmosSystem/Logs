(ns atmos-logs.core)

(def log-types [::info ::trace ::warn ::debug ::error ::exception ::fatal])

(defprotocol IPersistenceLogger
    "Protocol to persist log data"
    (log-> [log]))

(defprotocol ILoggerActions
    (info [data])
    (trace [data])
    (warn [data])
    (debug [data])
    (error [data])
    (exception [data])
    (fatal [data]))