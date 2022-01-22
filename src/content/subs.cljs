(ns content.subs
  (:require [content.db :as db]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::elements
 (fn [db [_ position]]
   (let [values (vals (::db/elements db))
         items (sort-by :sorting values)]
     (if position
       (filter #(= (:position %) position) items)
       items))))

(rf/reg-sub
 ::loading?
 (fn [db]
   (::db/loading? db)))
