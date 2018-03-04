(in-ns 'atmos-logs.core)

(def ^:private get-persist-logs-base* (-> (select* logs)))

(defget-identity-entity :logs [log-id] #(-> get-persist-logs-base*
                                                 (where %)
                                                 select) :id)

(defget-all-entity :logs get-persist-logs-base*)