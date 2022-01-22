(ns http.events
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(defn extend-keyword [kw val]
  (keyword (namespace kw) (str (name kw) val)))

(defn process [key]
  (extend-keyword key "-process"))

(defn success [key]
  (extend-keyword key "-success"))

(defn failure [key]
  (extend-keyword key "-failure"))

(rf/reg-event-fx
 ::request
 (fn-traced
  [_ [_ event request]]
  {:fx [[:dispatch [(process event)]]]
   :http-xhrio
   (merge
    {:method :get}
    (if (string? request) {:uri request} request)
    {:response-format (ajax/json-response-format {:keywords? true})
     :on-success [(success event)]
     :on-failure [(failure event)]})}))