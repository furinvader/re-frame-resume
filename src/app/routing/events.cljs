(ns app.routing.events
  (:require [app.api.events :as api]
            [app.routing.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::path-changed
 (fn-traced
  [{:keys [db]} [_ path]]
  {:db (assoc db ::db/path path)
   :fx [[:dispatch [::api/get-page {:path path}]]]}))
