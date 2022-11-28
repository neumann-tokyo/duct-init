(ns test-utils
  (:require [clojure.java.io :as io]
            [duct.core :as duct]
            [integrant.core :as ig]
            [jsonista.core :as j]))

(duct/load-hierarchy)

(defonce system
  (-> (duct/read-config (io/resource "duct_init/config.edn"))
      (duct/prep-config [:duct.profile/test :duct.profile/local])
      (ig/init)))

(defn ig-get [key]
  (get system key))

(def iso8601-pattern
  #"\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}Z")

(defn json-parse [json-string]
  (j/read-value json-string j/keyword-keys-object-mapper))

(defn to-json [hashmap]
  (j/write-value-as-string hashmap))

(def test-host
  "http://localhost:3001")

(defn url [& path]
  (apply str (cons test-host path)))
