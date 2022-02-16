(ns app.routing.events
  (:require [app.entities.events :as entities]
            [app.routing.db :as db]
            [app.routing.fx :as fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]
            [reagent.core :as r]))

(rf/reg-event-fx
 ::initialize
 (fn-traced
  []
  {:fx [[:dispatch [::entities/get-pages]]
        [::fx/window-title nil]]}))

(rf/reg-event-fx
 ::path-changed
 (fn-traced
  [{:keys [db]} [_ path]]
  (r/flush)
  {:db (assoc db ::db/path path)
   :fx [[:dispatch [::entities/get-page {:path path}]]]}))
