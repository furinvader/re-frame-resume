(ns app.routing.events
  (:require [app.entities.events :as entities]
            [app.routing.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::initialize
 (fn-traced
  []
  {:dispatch [::entities/get-pages]}))

(rf/reg-event-fx
 ::path-changed
 (fn-traced
  [{:keys [db]} [_ path]]
  {:db (assoc db ::db/path path)
   :fx [[:dispatch [::entities/get-page {:path path}]]]}))
