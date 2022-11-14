(ns duct-init.boundary.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]))

(t/deftest boundary-articles-create-test
  (t/testing "create an article"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})]
      (t/is (= (:articles/title article) "hello"))
      (t/is (= (:articles/body article) "world")))))

(t/deftest boundary-articles-update-test
  (t/testing "update an article"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          update (tu/ig-get :duct-init.boundary.articles/update)
          get-by-id (tu/ig-get :duct-init.boundary.articles/get-by-id)
          article (create {:title "hello" :body "world"})
          _ (update {:id (:articles/id article)
                     :title "foo"
                     :body "bar"})
          article (get-by-id (:articles/id article))]
      (t/is (= (:articles/title article) "foo"))
      (t/is (= (:articles/body article) "bar")))))
