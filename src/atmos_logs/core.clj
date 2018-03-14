(ns atmos-logs.core
  (:require [korma.core :refer :all]
            [atmos-rdb-kernel.core :refer [defadd-entity
                                           defget-identity-entity
                                           defget-all-entity]]
            [clj-time.core :refer [now to-time-zone]]
            [clj-time.coerce :refer [from-sql-time]]
            [clj-time.format :refer [formatter formatter-local unparse]])
  (:import (clojure.lang PersistentArrayMap)))


(def ^:private date-format "yyyy-MM-dd HH:mm:ss")
(def ^:private joda-date-formatter (formatter date-format))

(defn joda-time-zone-date-formatter
  [format]
  (formatter-local format))

(defn utc-now [] (unparse joda-date-formatter (now)))

(defn convert-time-zone
  [date time-zone format]
  (unparse (joda-time-zone-date-formatter format) (to-time-zone (from-sql-time date) time-zone)))


(defprotocol ILogRepository
  (add-log [data]))

(defprotocol ILogIdentityRepository
  (get-log [data time-zone format]))

(load "implementation")