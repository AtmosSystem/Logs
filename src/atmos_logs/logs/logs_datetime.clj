(in-ns 'atmos-logs.core)

(def ^:private date-format "yyyy-MM-dd HH:mm:ss")
(def ^:private joda-date-formatter (formatter date-format))

(defn joda-time-zone-date-formatter
  [format]
  (formatter-local format))

(defn utc-now-string [] (unparse joda-date-formatter (now)))

(defn convert-time-zone
  [date time-zone format]
  (unparse (joda-time-zone-date-formatter format) (to-time-zone (from-sql-time date) time-zone)))