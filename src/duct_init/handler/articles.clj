(ns duct-init.handler.articles
  (:require [integrant.core :as ig]))

(defmethod ig/init-key ::index [_ conf]
  (fn [req]
    {:status 200
     :body "ok"}))

(defmethod ig/init-key ::show [_ conf]
  (fn [req]
    {:status 200
     :body "ok"}))

(defmethod ig/init-key ::create [_ conf]
  (fn [req]
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
