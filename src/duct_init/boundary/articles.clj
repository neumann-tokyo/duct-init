(ns duct-init.boundary.articles
  (:require [integrant.core :as ig]
            [next.jdbc :as jdbc]))

(defmethod ig/init-key ::create [_ {:keys [db]}]
  (fn [{:keys [title body]}]
    (jdbc/execute-one! db
                       ["insert into 
                           articles(title,body,created_at,updated_at)
                         values
                           (?, ?, current_timestamp, current_timestamp)"
                        title body]
                       {:return-keys true})))
