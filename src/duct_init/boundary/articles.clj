(ns duct-init.boundary.articles
  (:require [integrant.core :as ig]
            [next.jdbc.sql :as sql]
            [next.jdbc.date-time]
            [java-time.api :as jt]
            [duct-init.util :as util]))

(defmethod ig/init-key ::create [_ {:keys [db]}]
  (fn [{:keys [title body]}]
    (-> db
        (sql/insert! :articles
                     {:title title
                      :body body})
        util/structured)))

(defmethod ig/init-key ::update [_ {:keys [db]}]
  (fn [{:keys [id title body]}]
    (sql/update! db :articles
                 {:title title
                  :body body
                  :updated_at (jt/instant)}
                 {:id id})))

;; https://stackoverflow.com/questions/19617336/clojure-deep-merge-hash-maps-with-dot-separated-string-keys
(defmethod ig/init-key ::get-by-id [_ {:keys [db]}]
  (fn [id]
    (-> db
        (sql/get-by-id :articles id)
        util/structured)))
