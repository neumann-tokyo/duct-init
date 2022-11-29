(ns duct-init.integration.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]
            [hato.client :as hc]
            [matcher-combinators.test]
            [matcher-combinators.matchers :as m]))

(t/deftest integration-articles-show-test
  (t/testing "integration articles show"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})
          response (hc/get (tu/url "/articles/" (-> article :articles :id)))]
      (t/is (= (:status response) 200))
      (t/is (match? {:articles {:id int?
                                :title "hello"
                                :body "world"
                                :created_at (m/regex tu/iso8601-pattern)
                                :updated_at (m/regex tu/iso8601-pattern)}}
                    (tu/json-parse (:body response)))))))

(t/deftest integration-articles-create-test
  (t/testing "integration articles create"
    (let [body {:title "hello"
                :body "world"}
          response (hc/post (tu/url "/articles/")
                            {:body (tu/to-json body) :content-type :json})]
      (t/is (= (:status response) 200))
      (t/is (match? {:articles {:id int?
                                :title "hello"
                                :body "world"
                                :created_at (m/regex tu/iso8601-pattern)
                                :updated_at (m/regex tu/iso8601-pattern)}}
                    (tu/json-parse (:body response)))))))

(t/deftest integration-articles-update-test
  (t/testing "integration articles update"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})
          article-id (-> article :articles :id)
          body {:title "foo" :body "bar"}
          response (hc/post (tu/url "/articles/" article-id)
                            {:body (tu/to-json body) :content-type :json})
          get-by-id (tu/ig-get :duct-init.boundary.articles/get-by-id)
          article (get-by-id article-id)]
      (t/is (= (:status response) 200))
      (t/is (= (:body response) "ok"))
      (t/is (match? {:articles {:id article-id
                                :title "foo"
                                :body "bar"}}
                    article)))))

(t/deftest integration-articles-delete-test
  (t/testing "integration articles delete"
    (let [create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})
          article-id (-> article :articles :id)
          response (hc/post (tu/url "/articles/" article-id "/delete"))
          get-by-id (tu/ig-get :duct-init.boundary.articles/get-by-id)]
      (t/is (= (:status response) 200))
      (t/is (= (:body response) "ok"))
      (t/is (empty? (get-by-id article-id))))))
