(ns duct-init.db
  (:require [integrant.core :as ig]))

(defmethod ig/init-key ::take-datasource [_ {:keys [db]}]
  (-> db :spec :datasource))
