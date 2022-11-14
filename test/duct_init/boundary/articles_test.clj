(ns duct-init.boundary.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]))

(t/deftest boundary-articles-create-test
  (t/testing "create an article"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})]
      (t/is (= (get-in article [:articles :title]) "hello"))
      (t/is (= (get-in article [:articles :body]) "world")))))

(t/deftest boundary-articles-update-test
  (t/testing "update an article"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          update (tu/ig-get :duct-init.boundary.articles/update)
          get-by-id (tu/ig-get :duct-init.boundary.articles/get-by-id)
          article (create {:title "hello" :body "world"})
          _ (update {:id (get-in article [:articles :id])
                     :title "foo"
                     :body "bar"})
          article (get-by-id (get-in article [:articles :id]))]
      (t/is (= (get-in article [:articles :title]) "foo"))
      (t/is (= (get-in article [:articles :body]) "bar")))))
