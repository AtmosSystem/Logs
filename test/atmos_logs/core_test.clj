(ns atmos-logs.core-test
  (:require [clojure.test :refer :all]
            [atmos-logs.core :refer :all]
            [atmos-kernel.core :refer [read-resource-edn]]
            [atmos-rdb-kernel.core :refer [defpersistence init-persistence]]))

(def configuration (read-resource-edn :config-dev))

(-> configuration :database defpersistence init-persistence)

(deftest log-repository-testing
  (let [mock-log {:user_entity_id 1
                  :log_type       "INFO"
                  :date           (utc-now-string)
                  :module         "Mock Module"
                  :note           "Mock note"}
        test-id 1
        id-inserted (add-log mock-log)]
    (testing "Insert data"
      (is (number? id-inserted)))
    (testing "Retrieve single data"
      (is (= test-id (-> test-id get-log :id))))))
