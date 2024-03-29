(ns app.subs
  (:require [app.entities.subs :as entities]
            [app.routing.subs :as routing]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::contents-by-position
 :<- [::routing/current-page]
 :<- [::entities/query [:contents [:page :position]]]
 (fn [[page contents] [_ position]]
   (get-in contents [(:id page) position])))

(rf/reg-sub
 ::loading?
 :<- [::routing/current-page]
 #(empty? (:contents %)))
