(ns duct-init.handler.articles
  (:require [integrant.core :as ig]))

(defmethod ig/init-key ::index [_ conf]
  (fn [req]
    {:status 200
     :body "ok"}))

(defmethod ig/init-key ::show [_ {:keys [articles-get-by-id]}]
  (fn [req]
    (let [id (get-in req [:path-params :id])
          article (articles-get-by-id (Integer/parseInt id))]
      {:status 200
       :body article})))

(defmethod ig/init-key ::create [_ conf]
  (fn [req]
    (let [aaa (:body-params req)])
    {:status 200
     :body "ok"}))

(defmethod ig/init-key ::update [_ conf]
  (fn [req]
    {:status 200
     :body "ok"}))

(defmethod ig/init-key ::delete [_ conf]
  (fn [req]
    {:status 200
     :body "ok"}))
