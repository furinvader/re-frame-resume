(ns app.events
  (:require [app.api.events :as api]
            [app.db :as db]
            [app.fx :as fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::initialize
 (fn-traced
  []
  {:db db/default-db
   :fx [[:dispatch [::api/init-pages]]]}))

(rf/reg-event-fx
 ::view-ready
 (fn-traced
  []
  {:fx [[::fx/remove-preloader]]}))

(rf/reg-event-fx
 ::set-title
 (fn-traced
  [_ [_ title]]
  {::fx/title title}))
