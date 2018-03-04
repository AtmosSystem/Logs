(ns atmos-logs.core
  (:require [korma.core :refer :all]
            [atmos-rdb-kernel.core :refer [defadd-entity
                                           defget-identity-entity
                                           defget-all-entity]]
            [clj-time.core :refer [now]]
            [clj-time.format :refer [formatter unparse]])
  (:import (clojure.lang PersistentArrayMap)))

(defprotocol ILogRepository
  (add-log [data]))

(defprotocol ILogIdentityRepository
  (get-log [data]))

(load "implementation")