(ns app.db
  (:require [app.content.db :as content]
            [app.entities.db :as entities]))

(def default-db
  (merge
   content/default-db
   entities/default-db))
