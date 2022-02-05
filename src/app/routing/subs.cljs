(ns app.routing.subs
  (:require [app.routing.db :as db]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::path
 #(::db/path %1))
