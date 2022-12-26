(ns atmos-logs.spec
    (:require [clojure.spec.alpha :as s]
              [atmos-kernel.spec :as ks]))

(s/def :data/message ::ks/non-blank-string)
(s/def :data/extra-data map?)
(s/def :data/exception-data map?)
(s/def ::log-data (s/keys :req-un [:data/message]
                          :opt-un [:data/extra-data :data/exception-data]))

(defn log-valid? [log-data]
    (s/valid? ::log-data log-data))