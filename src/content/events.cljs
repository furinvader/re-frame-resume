(ns content.events
  (:require [ajax.core :as ajax]
            [content.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::load-elements
 (fn-traced []
            {:http-xhrio
             {:method :get
              :uri "api/content"
              :response-format (ajax/json-response-format {:keywords? true})
              :on-success [::load-elements-success]
              :on-failure [::load-elements-failure]}}))

(rf/reg-event-db
 ::load-elements-success
 (fn-traced [db [_ elements]]
            (assoc db ::db/elements elements)))

(rf/reg-event-db
 ::load-elements-failure
 (fn-traced [db [_ _]]
            (assoc db ::db/elements [])))