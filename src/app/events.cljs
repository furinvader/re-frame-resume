(ns app.events
  (:require
   [app.db :as db]
   [content.events]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced
  [_ _]
  db/default-db))

(rf/reg-event-fx
 ::initialize
 (fn-traced
  []
  {:db db/default-db
   :fx [[:dispatch [::content.events/load-elements]]]}))
