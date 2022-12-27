(ns atmos-logs.web.api
    (:require [atmos-web-kernel-reitit.core :as web]
              [atmos-kernel.serializer.core :as ksc]
              [atmos-logs.core :as c]
              [atmos-logs.spec :as log-spec]
              [atmos-logs.web.spec :as s]))

(def log-types-actions (into [] (zipmap c/log-types
                                        ['c/info 'c/trace 'c/warn 'c/debug 'c/error 'c/exception 'c/fatal])))

(defn log-handler
    [log-action]
    (web/web-handler
        (fn [{:keys [body-params]}]
            (let [data (ksc/de-serialize body-params s/de-serializer-map)]
                (log-action data)))))

(defn generate-log-routes
    [log-actions]
    (for [[route-name log-action] log-actions
          :let [route-path (str "/" (name route-name) "/")]]
        [route-path {:name       route-name
                     :parameters {:body ::log-spec/log-data}
                     :post       (log-handler log-action)}]))


(def routes ["/logs" {:name ::log} (generate-log-routes log-types-actions)])