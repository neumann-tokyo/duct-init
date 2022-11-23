(ns duct-init.integration.articles-test
  (:require [clojure.test :as t]
            [test-utils :as tu]
            [hato.client :as hc]
            [matcher-combinators.test]
            [matcher-combinators.matchers :as m]))

(t/deftest integration-articles-show-test
  (t/testing "integration articles show"
    (let [system (tu/go)
          create (tu/ig-get :duct-init.boundary.articles/create)
          article (create {:title "hello" :body "world"})
          response (hc/get (str tu/test-host "/articles/" (-> article :articles :id)))]
      (t/is (= (:status response) 200))
      (t/is (match? {:articles {:id int?
                                :title "hello"
                                :body "world"
                                :created_at (m/regex tu/iso8601-pattern)
                                :updated_at (m/regex tu/iso8601-pattern)}}
                    (tu/json-parse (:body response))))
      (tu/halt system))))

(t/deftest integration-articles-create-test
  (t/testing "integration articles create"
    (let [system (tu/go)
          body {:title "hello"
                :body "world"}
          response (hc/post (str tu/test-host "/articles/")
                            {:body (tu/to-json body) :content-type :json})]
      (t/is (= (:status response) 200))
      (t/is (= (:body response) "ok"))
      (tu/halt system))))
