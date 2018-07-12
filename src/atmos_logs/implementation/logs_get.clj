(in-ns 'atmos-logs.implementation.core)

(def ^:private get-persist-logs-base* (-> (select* logs)))

(declare get-all-logs get-first-logs*)

(defget-identity-entity logs get-persist-logs-base* [id])

(defget-all-entity logs #(-> get-persist-logs-base*
                             select))