(ns atmos-logs.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocol]]))

(declare ILogProtocol add-log)

(defatmos-record-protocol :Log '[add-log])

(defmulti get-all (fn [entity request] (keyword entity)))