(ns app.entities.events
  (:require [app.entities.db :as db]
            [app.http.events :as http]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::get-pages
 (fn-traced
  []
  {:dispatch [::http/request [:get "pages"] [::get-pages-success]]}))

(rf/reg-event-db
 ::get-pages-success
 (fn-traced
  [db [_ pages]]
  (db/add-entities db :pages pages)))

(rf/reg-event-fx
 ::get-page
 (fn-traced
  [_ [_ params]]
  {:dispatch [::http/request [:get "page" params] [::get-page-success]]}))

(rf/reg-event-db
 ::get-page-success
 (fn-traced
  [db [_ page]]
  (db/add-entities db :pages [page])))
