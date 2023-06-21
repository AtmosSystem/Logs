(ns atmos-logs.web.api
    (:require [atmos-kernel.serializer.core :as ksc]
              [atmos-logs.core :as c]
              [atmos-logs.spec :as log-spec]
              [atmos-logs.web.spec :as s]
              [atmos-web-kernel-reitit.core :as web]))

(defn log-handler
    [log-action]
    (web/web-handler
        (fn [{:keys [body-params]}]
            (let [data (ksc/de-serialize body-params s/de-serializer-map)]
                (log-action data)))))

(defn log-route
    [route-name log-action]
    {:name       route-name
     :parameters {:body ::log-spec/log-data}
     :post       (log-handler log-action)})

(def routes
    ["/logs"
     ["/info/" (log-route ::info c/info)]
     ["/trace/" (log-route ::trace c/trace)]
     ["/warn/" (log-route ::warn c/warn)]
     ["/debug/" (log-route ::debug c/debug)]
     ["/error/" (log-route ::error c/error)]
     ["/exception/" (log-route ::exception c/exception)]
     ["/fatal/" (log-route ::fatal c/fatal)]])