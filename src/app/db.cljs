(ns app.db
  (:require [app.content.db :as content]
            [app.entities.db :as entities]
            [app.routing.db :as routing]))

(def default-db
  (merge
   content/default-db
   entities/default-db
   routing/default-db))
