(ns atmos-logs.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocols]])
  (:import (java.util Map)))

(defprotocol ILogRepository
  (add-log [data]))

(defprotocol ILogIdentityRepository
  (get-log [data]))
