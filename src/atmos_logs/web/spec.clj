(ns atmos-logs.web.spec
    (:require [clojure.spec.alpha :as s]
              [atmos-kernel.spec :as ks]
              [atmos-kernel.core :as k]
              [atmos-logs.core :as c]
              [reitit.spec :as rs]))

(s/def :api.data/type (s/and ::ks/non-blank-string #(k/in? (map name c/log-types) %)))
(s/def :api.data/message ::ks/non-blank-string)
(s/def :api.data/extra-data map?)
(s/def :api.data/exception-data map?)
(s/def ::log-data (s/keys :req-un [:api.data/type :api.data/message]
                          :opt-un [:api.data/extra-data :api.data/exception-data]))
(def de-serializer-map {:data-spec ::log-data})

(s/def :api.route.parameters/name (s/and keyword? #(k/in? c/log-types %)))
(s/def :api.route.parameters.method/post (s/keys :req-un [::rs/handler]))
(s/def :api.route.parameters/body #(= ::log-data %))
(s/def :api.route/parameters (s/keys :req-un [:api.route.parameters/body]))
(s/def ::log-route-api (s/merge ::rs/default-data (s/keys :req-un [:api.route.parameters/name
                                                                   :api.route.parameters.method/post
                                                                   :api.route/parameters])))