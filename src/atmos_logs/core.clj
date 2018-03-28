(ns atmos-logs.core
  (:require [korma.core :refer :all]
            [atmos-rdb-kernel.core :refer [defadd-entity
                                           defget-identity-entity
                                           defget-all-entity]]
            [clj-time.core :refer [now to-time-zone]]
            [clj-time.coerce :refer [from-sql-time]]
            [clj-time.format :refer [formatter formatter-local unparse]])
  (:import (java.util Map)))

(defprotocol ILogRepository
  (add-log [data]))

(defprotocol ILogIdentityRepository
  (get-log [data]))

(load "implementation")