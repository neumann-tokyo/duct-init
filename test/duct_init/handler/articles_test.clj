(ns duct-init.handler.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]))

(t/deftest handler-articles-show-test
  (t/testing "articles show handler"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})
          handler (tu/ig-get :duct-init.handler.articles/show)
          response (handler {:path-params {:id (-> article :articles :id str)}})]
      (t/is (int? (get-in response [:body :articles :id])))
      (t/is (= (get-in response [:body :articles :title]) "hello"))
      (t/is (= (get-in response [:body :articles :body]) "world"))
      (t/is (instance? java.sql.Timestamp (get-in response [:body :articles :created_at])))
      (t/is (instance? java.sql.Timestamp (get-in response [:body :articles :updated_at]))))))
