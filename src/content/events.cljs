(ns content.events
  (:require [content.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [http.events :as http]
            [re-frame.core :as rf]))

(defn normalize [entities]
  (into {} (map #(vector (:id %) %) entities)))

(rf/reg-event-fx
 ::load-pages
 (fn-traced
  [_ [event]]
  {:dispatch [::http/request event "pages"]}))

(rf/reg-event-fx
 ::load-pages-success
 (fn-traced
  [{:keys [db]} [_ pages]]
  {:db (assoc db ::db/pages (normalize pages))
   :fx [[:dispatch [::load-elements (:id (get pages 0))]]]}))

(rf/reg-event-db
 ::load-pages-failure
 (fn-traced
  [db [_ pages]]
  (assoc db ::db/pages {})))

(rf/reg-event-fx
 ::load-elements
 (fn-traced
  [_ [event id]]
  {:dispatch [::http/request event {:uri "contents" :params {:page id}}]}))

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