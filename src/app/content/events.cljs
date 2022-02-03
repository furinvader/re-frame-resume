(ns app.content.events
  (:require [app.content.db :as db]
            [app.entities.events :as entities]
            [app.http.events :as http]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(defn normalize [entities]
  (into {} (map #(vector (:id %) %) entities)))

(rf/reg-event-fx
 ::load-pages
 (fn-traced
  [_ [event]]
  {:dispatch [::http/request event [:get "pages"]]}))

(rf/reg-event-fx
 ::load-pages-success
 (fn-traced
  [_ [_ pages]]
  {:fx [[:dispatch [::load-elements (:id (get pages 0))]]
        [:dispatch [::entities/add :pages pages]]]}))

(rf/reg-event-db
 ::load-pages-failure
 (fn-traced
  [db [_ pages]]
  (assoc db ::db/pages {})))

(rf/reg-event-fx
 ::load-elements
 (fn-traced
  [_ [event id]]
  {:dispatch [::http/request event [:get "contents" {:page id}]]}))

(rf/reg-event-db
 ::load-elements-success
 (fn-traced
  [db [_ elements]]
  (assoc db ::db/elements (normalize elements))))

(rf/reg-event-db
 ::load-elements-failure
 (fn-traced
  [db [_ _]]
  (assoc db ::db/elements {})))