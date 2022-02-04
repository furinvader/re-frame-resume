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
  []
  {:dispatch [::http/request [:get "pages"]
              [[::load-pages-success]]]}))

(rf/reg-event-fx
 ::load-pages-success
 (fn-traced
  [_ [_ pages]]
  {:fx [[:dispatch [::load-elements (:id (get pages 0))]]
        [:dispatch [::entities/add :pages pages]]]}))

(rf/reg-event-fx
 ::load-elements
 (fn-traced
  [_ [_ id]]
  {:dispatch [::http/request [:get "contents" {:page id}]
              [[::load-elements-success]]]}))

(rf/reg-event-db
 ::load-elements-success
 (fn-traced
  [db [_ elements]]
  (assoc db ::db/elements (normalize elements))))
