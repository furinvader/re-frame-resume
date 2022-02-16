(ns app.routing.subs
  (:require [app.entities.subs :as entities]
            [app.routing.db :as db]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::path
 #(::db/path %))

(rf/reg-sub
 ::current-page
 :<- [::entities/query [:pages :by-path]]
 :<- [::path]
 #(apply get %))

(rf/reg-sub
 ::page-title
 :<- [::current-page]
 #(:title %))
