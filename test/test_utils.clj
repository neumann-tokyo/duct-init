(ns test-utils
  (:require [clojure.java.io :as io]
            [duct.core :as duct]
            [integrant.core :as ig]))

(duct/load-hierarchy)

(def ^:private config
  (atom
   (-> (duct/read-config (io/resource "duct_init/config.edn"))
       (duct/prep-config [:duct.profile/test :duct.profile/local]))))

(defn ig-get [key]
  (-> @config
      (ig/init [key])
      (get key)))
