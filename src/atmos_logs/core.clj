(ns atmos-logs.core
    (:require
        [atmos-logs.spec :as spec]
        [clojure.spec.alpha :as s]
        [clojure.spec.gen.alpha :as gen]))

(def log-types [:info :trace :warn :debug :error :exception :fatal])

(defprotocol PLogger
    (logger-name [logger])
    (log-> [logger log-type data]))

(defprotocol PLoggerActions
    (info [logger data])
    (trace [logger data])
    (warn [logger data])
    (debug [logger data])
    (error [logger data])
    (exception [logger ex])
    (fatal [logger data]))

(s/def ::logger (s/with-gen (s/or :p-logger #(satisfies? PLogger %) :p-logger-actions #(satisfies? PLoggerActions %))
                            #(gen/return (reify
                                             PLogger
                                             (logger-name [_] "Generic logger")
                                             (log-> [_ _ _] true)

                                             PLoggerActions
                                             (info [_ _] true)
                                             (trace [_ _] true)
                                             (warn [_ _] true)
                                             (debug [_ _] true)
                                             (error [_ _] true)
                                             (exception [_ _] true)
                                             (fatal [_ _] true)))))

(s/def ::log-type (s/with-gen (s/and keyword? #(some (fn [lt] (= lt %)) log-types))
                              #(gen/elements log-types)))

(s/fdef logger-name
        :args (s/cat :logger ::logger)
        :ret string?)

(s/fdef log->
        :args (s/cat :logger ::logger :log-type ::log-type ::data ::spec/log-data-or-exception)
        :ret boolean?)

(s/fdef info
        :args (s/cat :logger ::logger :data ::spec/log-data)
        :ret boolean?)

(s/fdef trace
        :args (s/cat :logger ::logger :data ::spec/log-data)
        :ret boolean?)

(s/fdef warn
        :args (s/cat :logger ::logger :data ::spec/log-data)
        :ret boolean?)

(s/fdef debug
        :args (s/cat :logger ::logger :data ::spec/log-data)
        :ret boolean?)

(s/fdef error
        :args (s/cat :logger ::logger :data ::spec/log-data)
        :ret boolean?)

(s/fdef exception
        :args (s/cat :logger ::logger :ex ::spec/exception)
        :ret boolean?)

(s/fdef fatal
        :args (s/cat :logger ::logger :data ::spec/log-data)
        :ret boolean?)