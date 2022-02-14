(ns app.entities.db
  (:require [compound2.core :as c]))

(def default-db
  {::compounds
   {:pages
    (c/compound 
     [{:id :by-id
       :kfn :id
       :on-conflict merge}
      {:id :by-path
       :kfn :path
       :index-type :one-to-one
       :on-conflict merge}])
    :contents
    (c/compound
     [{:id :by-id
       :kfn :id}
      {:id :by-page-id
       :kfn :page
       :index-type :one-to-many}
      {:path [:page :position]
       :index-type :nested-to-many}])}})

(defn normalize [compounds type entities]
  (as-> compounds cs
    (update cs type c/add-items entities)
    (case type
      :pages 
      (->> entities
           (map :contents)
           (flatten)
           (normalize cs :contents))
      cs)
    ))

(defn add-entities [db type entities]
  (update db ::compounds normalize type entities))
