(ns atmos-logs.api
    (:require [atmos-logs.core :as c]
              [atmos-logs.spec :as log-spec]
              [atmos-web-kernel-reitit.core :as atmos-http]
              [reitit.coercion.spec]))

(defn log-handler
    [log-action]
    (atmos-http/request-handler
        (fn [{:keys [parameters]}]
            (let [data (-> parameters :body)]
                (log-action data)))))

(defn log-route
    [route-name log-action]
    {:name       route-name
     :coercion   reitit.coercion.spec/coercion
     :parameters {:body ::log-spec/log-data}
     :post       (log-handler log-action)})

(def routes
    ["/logs"
     ["/info" (log-route ::info c/info)]
     ["/trace" (log-route ::trace c/trace)]
     ["/warn" (log-route ::warn c/warn)]
     ["/debug" (log-route ::debug c/debug)]
     ["/error" (log-route ::error c/error)]
     ["/exception" (log-route ::exception c/exception)]
     ["/fatal" (log-route ::fatal c/fatal)]])