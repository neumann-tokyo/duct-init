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

(defmethod ig/init-key ::create [_ {:keys [articles-create]}]
  (fn [{:keys [body-params]}]
    (let [article (articles-create body-params)]
      {:status 200
       :body article})))

(defmethod ig/init-key ::update [_ {:keys [articles-update]}]
  (fn [{:keys [body-params path-params]}]
    (let [id (-> path-params :id Integer/parseInt)
          _ (articles-update (assoc body-params :id id))]
      {:status 200
       :body "ok"})))

(defmethod ig/init-key ::delete [_ {:keys [articles-delete]}]
  (fn [{:keys [path-params]}]
    (let [id (-> path-params :id Integer/parseInt)
          _ (articles-delete id)]
      {:status 200
       :body "ok"})))
