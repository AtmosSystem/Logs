(ns atmos-logs.web.api
    (:require [atmos-logs.core :as c]
              [atmos-logs.web.serializers :as s]
              [atmos-web-kernel-reitit.core :as web]))

(defn not-found-response [_] (web/web-response "route not found" :bad-request))

(defn generate-log-routes
    [log-actions]
    (for [[route-name _] log-actions
          :let [route-path (str "/" (name route-name) "/")]]
        [route-path {:name route-name
                     :post (web/web-request (fn [_] (name route-name)))}]))


(def router (web/web-router ["/log" {:name ::log}
                             (generate-log-routes [[::info 'c/info]
                                                   [::debug 'c/debug]
                                                   [::error 'c/error]
                                                   [::exception 'c/exception]
                                                   [::fatal 'c/fatal]])]))

(def app (web/ring-app router not-found-response))