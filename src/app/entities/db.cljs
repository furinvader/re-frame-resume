(ns app.entities.db
  (:require [compound2.core :as c]))

(def default-db
  {::compounds
   {:pages (c/compound [{:id :by-id :kfn :id}])}})
