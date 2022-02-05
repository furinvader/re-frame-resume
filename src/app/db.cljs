(ns app.db
  (:require [app.entities.db :as entities]
            [app.routing.db :as routing]))

(def default-db
  (merge
   entities/default-db
   routing/default-db))
