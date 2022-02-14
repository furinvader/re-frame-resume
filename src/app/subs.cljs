(ns app.subs
  (:require [app.entities.subs :as entities]
            [app.routing.subs :as routing]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::current-page
 :<- [::routing/current-page]
 identity)

(rf/reg-sub
 ::contents-by-position
 :<- [::current-page]
 :<- [::entities/query [:contents [:page :position]]]
 (fn [[page contents] [_ position]]
   (get-in contents [(:id page) position])))

(rf/reg-sub
 ::navigation
 :<- [::entities/items :pages]
 #(sort-by :sorting %))

(rf/reg-sub
 ::loading?
 :<- [::current-page]
 #(empty? (:contents %)))
