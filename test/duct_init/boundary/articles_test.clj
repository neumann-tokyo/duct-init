(ns duct-init.boundary.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]))

(t/deftest boundary-articles-create-test
  (t/testing "foo"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)]
      (t/is (= (create {:title "hello" :body "world"}) "helloworld")))))
