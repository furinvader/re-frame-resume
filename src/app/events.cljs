(ns app.events
  (:require [app.db :as db]
            [app.fx :as fx]
            [app.content.events :as content.events]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::initialize
 (fn-traced
  []
  {:db db/default-db
   :fx [[:dispatch [::content.events/load-pages]]]}))

(rf/reg-event-fx
 ::view-ready
 (fn-traced
  []
  {:fx [[::fx/remove-preloader]]}))
