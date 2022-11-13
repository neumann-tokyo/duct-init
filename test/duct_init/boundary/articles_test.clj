(ns duct-init.boundary.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]))

(t/deftest boundary-articles-create-test
  (t/testing "create an article"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})]
      (t/is (= (:articles/title article) "hello"))
      (t/is (= (:articles/body article) "world")))))
