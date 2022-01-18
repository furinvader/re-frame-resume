(ns app.events
  (:require [ajax.core :as ajax]
            [app.db :as db]
            [content.events]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(rf/reg-event-fx
 ::initialize
 (fn-traced []
            {:db db/default-db
             :fx [[:dispatch [::content.events/load-elements]]]}))

(rf/reg-event-fx
 ::hello-world
 (fn [{:keys [db]}]
   {:db (assoc db :hello-world "...loading...")
    :http-xhrio {:method :get
                 :uri "api/hello-world"
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::hello-world-success]}}))

(rf/reg-event-db
 ::hello-world-success
 (fn-traced [db [_ text]]
            (assoc db :hello-world text)))
