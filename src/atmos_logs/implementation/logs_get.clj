(in-ns 'atmos-logs.implementation.core)

(def ^:private get-persist-logs-base* (-> (select* logs)))
(def ^:private get-persist-logs-by #(-> get-persist-logs-base*
                                        (where %)
                                        select))

(declare get-all-logs get-first-logs*)

(defget-identity-entity logs get-persist-logs-by [id])

(defget-all-entity logs #(-> get-persist-logs-base*
                             select))