(ns atmos-logs.web.spec
    (:require [clojure.spec.alpha :as s]
              [atmos-kernel.core :as k]
              [atmos-logs.core :as c]
              [atmos-logs.spec :as log-spec]
              [reitit.spec :as rs]))

(def de-serializer-map {:data-spec ::log-spec/log-data})

(s/def :api.route.parameters/name (s/and keyword? #(k/in? c/log-types %)))
(s/def :api.route.parameters.method/post (s/keys :req-un [::rs/handler]))
(s/def :api.route.parameters/body #(= ::log-spec/log-data %))
(s/def :api.route/parameters (s/keys :req-un [:api.route.parameters/body]))
(s/def ::log-route-api (s/merge ::rs/default-data (s/keys :req-un [:api.route.parameters/name
                                                                   :api.route.parameters.method/post
                                                                   :api.route/parameters])))