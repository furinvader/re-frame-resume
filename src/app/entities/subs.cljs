(ns app.entities.subs
  (:require [app.entities.db :as db]
            [compound2.core :as c]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::compounds
 #(::db/compounds %))

(rf/reg-sub
 ::compound
 :<- [::compounds]
 #(get %1 (get %2 1)))

(rf/reg-sub
 ::items
 #(rf/subscribe [::compound (get % 1)])
 c/items)

(rf/reg-sub
 ::query
 :<- [::compounds]
 #(get-in %1 (get %2 1)))
