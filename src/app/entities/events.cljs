(ns app.entities.events
  (:require [app.entities.db :as db]
            [compound2.core :as c]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::add
 (fn-traced
  [db [_ type entities]]
  (update-in db [::db/compounds type] c/add-items entities)))
