(ns app.routing.subs
  (:require [app.entities.subs :as entities]
            [app.routing.db :as db]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::path
 #(::db/path %1))

(rf/reg-sub
 ::current-page
 :<- [::entities/query [:pages :by-path]]
 :<- [::path]
 #(apply get %))
