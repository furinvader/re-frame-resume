(ns app.api.events
  (:require [app.entities.events :as entities]
            [app.http.events :as http]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::init-pages
 (fn-traced
  []
  {:dispatch [::http/request [:get "pages"]
              [::init-pages-success]]}))

(rf/reg-event-fx
 ::init-pages-success
 (fn-traced
  [_ [_ pages]]
  {:dispatch [::entities/add :pages pages]}))

(rf/reg-event-fx
 ::get-page
 (fn-traced
  [_ [_ params]]
  {:dispatch [::http/request [:get "page" params]
              [::get-page-success]]}))

(rf/reg-event-fx
 ::get-page-success
 (fn-traced
  [_ [_ page]]
  {:fx [[:dispatch [::entities/add :pages [page]]]
        [:dispatch [::entities/add :contents (:contents page)]]]}))