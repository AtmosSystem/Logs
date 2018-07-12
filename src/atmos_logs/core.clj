(ns atmos-logs.core
  (:require [atmos-kernel.protocol :refer [defatmos-record-protocol]]))

(declare ILogProtocol ILogIdentityProtocol add-log get-log)

(defatmos-record-protocol :Log '[add-log])
(defatmos-record-protocol :LogIdentity '[get-log])