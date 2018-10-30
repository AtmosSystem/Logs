(ns atmos-logs.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocol]]))

(declare ILogProtocol add-log get-log)

(defatmos-record-protocol :Log '[add-log get-log])

(defmulti get-all (fn [entity request] (keyword entity)))