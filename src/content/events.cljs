(ns content.events
  (:require [content.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [http.events :as http]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::load-elements
 (fn-traced
  [_ [event]]
  {:fx [[:dispatch [::http/request event "api/content"]]]}))

(rf/reg-event-db
 ::load-elements-process
 (fn-traced
  [db]
  db))

(rf/reg-event-db
 ::load-elements-success
 (fn-traced
  [db [_ elements]]
  (assoc db ::db/elements elements)))

(rf/reg-event-db
 ::load-elements-failure
 (fn-traced [db [_ _]]
            (assoc db ::db/elements [])))