(ns app.events
  (:require [app.db :as db]
            [app.fx :as fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize
 (fn-traced [] db/default-db))

(rf/reg-event-fx
 ::view-ready
 (fn-traced
  []
  {:fx [[::fx/remove-preloader]]}))
