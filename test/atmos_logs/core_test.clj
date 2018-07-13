(ns atmos-logs.core-test
  (:require [clojure.test :refer :all]
            [atmos-logs.service :refer :all]))


(deftest log-repository-testing
  (let [mock-log {:log {:user_entity_id 1
                        :log_type       "INFO"
                        :module         "Mock Module"
                        :note           "Mock note"}}
        test-id 1
        id-inserted (add-logs mock-log)]
    (testing "Insert data"
      (is (number? id-inserted)))
    (testing "Retrieve single data"
      (is (= test-id (-> test-id get-logs :id))))))
