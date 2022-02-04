(ns app.http.events
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(goog-define base-url "")

(rf/reg-event-fx
 ::request
 (fn-traced
  [_ [_ [method uri params]  [success failure]]]
  {:http-xhrio
   (merge
    {:method method
     :uri (str base-url "/" uri)
     :params params
     :response-format (ajax/json-response-format {:keywords? true})
     :on-success success}
    (when-not (nil? failure)
      {:on-failure failure}))}))
