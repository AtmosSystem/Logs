(in-ns 'atmos-logs.implementation.core)

(defn utc-now-string [] (unparse joda-date-formatter (now)))