(ns resume.events
  (:require
   [re-frame.core :as re-frame]
   [ajax.core :as ajax]
   [resume.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-fx
 ::hello-world
 (fn [{:keys [db]}]
   {:db (assoc db :hello-world "...loading...")
    :http-xhrio {:method :get
                 :uri "https://resume.alexdik.de/api/hello-world"
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::hello-world-success]}}))

(re-frame/reg-event-db
 ::hello-world-success
 (fn-traced [db [_ text]]
            (assoc db :hello-world text)))
