(ns atmos-logs.web.serializers
    (:require [clojure.spec.alpha :as s]
              [atmos-kernel.spec :as ks]
              [atmos-kernel.core :as k]
              [atmos-logs.core :as c]
              [muuntaja.core :as m]
              [camel-snake-kebab.core :as csk]))

(s/def ::type (s/and ::ks/non-blank-string #(k/in? (map name c/log-types) %)))
(s/def ::message ::ks/non-blank-string)
(s/def ::extra-data map?)
(s/def ::exception-data map?)
(s/def ::log-data (s/keys :req-un [::type ::message]
                          :opt-un [::extra-data ::exception-data]))

(def de-serializer-map {:data-spec ::log-data})

(def muuntaja (m/create
                  (-> m/default-options
                      (assoc-in [:formats "application/json" :encoder-opts]
                                {:encode-key-fn #(csk/->camelCase (name %))})
                      (assoc-in [:formats "application/json" :decoder-opts]
                                {:decode-key-fn csk/->kebab-case-keyword}))))