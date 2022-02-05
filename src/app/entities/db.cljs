(ns app.entities.db
  (:require [compound2.core :as c]))

(def default-db
  {::compounds
   {:pages
    (c/compound 
     [{:id :by-id
       :kfn :id}
      {:id :by-path
       :kfn :path
       :index-type :one-to-one}])

    :contents
    (c/compound
     [{:id :by-id
       :kfn :id}
      {:id :by-page-id
       :kfn :page
       :index-type :one-to-many}
      {:path [:page :position]
       :index-type :nested-to-many}])}})
