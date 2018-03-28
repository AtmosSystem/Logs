(in-ns 'atmos-logs.core)

(def ^:private get-persist-logs-base* (-> (select* logs)))

(defget-identity-entity :logs [log-id] #(-> get-persist-logs-base*
                                            (where %)
                                            select) :id)

(defn get-all-logs
  []
  (let [data (-> get-persist-logs-base*
                 select)]
    data))