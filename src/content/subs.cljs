(ns content.subs
  (:require [content.db :as db]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::elements
 (fn [db]
   (::db/elements db)))
