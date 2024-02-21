(ns atmos-logs.spec
    (:require
        [atmos-kernel.spec :as ks]
        [clojure.spec.alpha :as s]
        [clojure.spec.gen.alpha :as gen]))

(s/def ::message (s/with-gen ::ks/non-blank-string
                             #(gen/fmap (fn [n] (str "A log message" "-" n)) (gen/int))))

(s/def ::extra-data (s/map-of keyword? string?))
(s/def ::exception (s/with-gen ::ks/exception
                               #(gen/elements [(Exception.) (InternalError.) (RuntimeException.)])))

(s/def ::log-data (s/keys :req-un [::message]
                          :opt-un [::extra-data]))

(s/def ::log-data-or-exception (s/or :data-map ::log-data
                                     :exception (s/or :exception-class ::ks/exception
                                                      :exception-map (s/map-of #{:via :trace :cause} any? :min-count 1))))

(defn log-data-or-exception-valid? [log-data]
    (s/valid? ::log-data-or-exception log-data))

(defn log-data-valid? [log-data]
    (s/valid? ::log-data log-data))