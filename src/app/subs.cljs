(ns app.subs
  (:require [re-frame.core :as rf]
            [app.content.db :as content.db]))

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
 #(sort-by :sorting (vals (::content.db/pages %))))
