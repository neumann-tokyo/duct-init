(ns duct-init.util
  (:require [clojure.string :as string]))

(defn keyname [key]
  (str (namespace key) "/" (name key)))

(defn structured [h]
  (reduce-kv
   (fn [h k v]
     (assoc-in
      h
      (map keyword (-> k keyname (string/split #"[/]")))
      (if (map? v)
        (structured v)
        v)))
   {}
   h))
