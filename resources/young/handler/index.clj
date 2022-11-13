(ns duct-init.handler.index
  (:require [integrant.core :as ig]))

(defmethod ig/init-key ::index [_ conf]
  (fn [req]
    {:status 200
     :body {:total "100"}}))
