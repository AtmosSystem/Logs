(ns atmos-logs.web.api
    (:require [atmos-web-kernel-reitit.core :as web]
              [atmos-web-kernel-reitit.ring.middleware :as atmos-middleware]
              [atmos-kernel.serializer.core :as ksc]
              [atmos-logs.core :as c]
              [atmos-logs.web.serializers :as s]
              [reitit.ring.middleware.muuntaja :as muuntaja-middleware]))

(def log-types-actions (into [] (zipmap c/log-types
                                        ['c/info 'c/debug 'c/error 'c/exception 'c/fatal])))

(defn not-found-response [_] (web/web-response "route not found" :404))

(defn log-handler
    [log-action]
    (web/web-handler
        (fn [{:keys [body-params]}]
            (let [data (ksc/de-serialize body-params s/de-serializer-map)]
                data))))

(defn generate-log-routes
    [log-actions]
    (for [[route-name log-action] log-actions
          :let [route-path (str "/" (name route-name) "/")]]
        [route-path {:name route-name
                     :post (log-handler log-action)}]))


(def router (web/web-router
                ["/log" {:name ::log} (generate-log-routes log-types-actions)]
                {:data {:muuntaja   s/muuntaja
                        :middleware [muuntaja-middleware/format-middleware
                                     atmos-middleware/web-response]}}))

(def app (web/ring-app router not-found-response))