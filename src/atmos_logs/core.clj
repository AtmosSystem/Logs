(ns atmos-logs.core
  (:require [atmos-kernel.core :refer [defatmos-record-protocols
                                       defatmos-seq-record-protocol]]
            [korma.core :refer :all]
            [atmos-rdb-kernel.core :refer [defadd-entity
                                           defget-entity
                                           defget-identity-entity
                                           defget-all-entity
                                           defupdate-entity
                                           defremove-entity]])
  (:import (clojure.lang PersistentVector PersistentArrayMap)))


(load "implementation")