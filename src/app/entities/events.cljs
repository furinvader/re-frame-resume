(ns app.entities.events
  (:require [app.entities.db :as db]
            [compound2.core :as c]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::add
 (fn-traced
  [{:keys [db]} [_ type entities]]
  {:db (update-in db [::db/compounds type] c/add-items entities)

   :fx [(when (= type :pages)
          (let [contents (flatten (map :contents entities))]
            [:dispatch [::add :contents contents]]))]}))

