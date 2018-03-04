(in-ns 'atmos-logs.core)

(defadd-entity :logs [log] #(insert logs (values %)))