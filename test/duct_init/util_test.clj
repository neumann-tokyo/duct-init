(ns duct-init.util-test
  (:require [clojure.test :as t]
            [duct-init.util :as util]))

(t/deftest structured-test
  (t/is (= (util/structured
            {:articles/id 1
             :articles/title "hello"
             :articles/body "world"})
           {:articles {:id 1
                       :title "hello"
                       :body "world"}})))
