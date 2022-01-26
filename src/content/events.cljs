(ns content.events
  (:require [content.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [http.events :as http]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::load-elements
 (fn-traced
  [_ [event]]
  {:dispatch [::http/request event "api/content/home"]}))

(rf/reg-event-db
 ::load-elements-success
 (fn-traced
  [db [_ elements]]
  (-> db
      (assoc ::db/elements elements)
      (assoc ::db/loading? false))))

(rf/reg-event-db
 ::load-elements-failure
 (fn-traced [db [_ _]]
            (assoc db ::db/elements [])))