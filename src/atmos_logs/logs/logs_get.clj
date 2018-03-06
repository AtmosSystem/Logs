(in-ns 'atmos-logs.core)

(def ^:private get-persist-logs-base* (-> (select* logs)))

(defget-identity-entity :logs [log-id] #(-> get-persist-logs-base*
                                            (where %)
                                            select) :id)

(defn get-all-logs
  [time-zone]
  (let [data (-> get-persist-logs-base*
                 select)]
    (map #(assoc % :date (convert-time-zone (:date %) time-zone)) data)))