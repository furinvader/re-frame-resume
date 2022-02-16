(ns app.routing.fx
  (:require [app.routing.subs :as routing.subs]
            [re-frame.core :as rf]))

(rf/reg-fx
 ::window-title
 (fn []
   (add-watch
    (rf/subscribe [::routing.subs/current-page])
    ::window-title
    #(set! (.-title js/document) (:title %4)))))
