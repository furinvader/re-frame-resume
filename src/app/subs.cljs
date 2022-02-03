(ns app.subs
  (:require [app.content.db :as content.db]
            [app.entities.subs :as entities]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::elements
 (fn [db [_ position]]
   (let [values (vals (::content.db/elements db))
         items (sort-by :sorting values)]
     (if position
       (filter #(= (:position %) position) items)
       items))))

(rf/reg-sub
 ::loading?
 #(empty? (::content.db/elements %)))

(rf/reg-sub
 ::navigation
 :<- [::entities/items :pages]
 identity)
