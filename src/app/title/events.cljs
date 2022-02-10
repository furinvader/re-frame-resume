(ns app.title.events
  (:require [app.title.fx :as fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::set-title
 (fn-traced
  [_ [_ title]]
  {::fx/title title}))
