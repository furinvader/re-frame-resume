(ns app.routing.events
  (:require [app.routing.db :as db]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::path-changed
 #(assoc %1 ::db/path (get %2 1)))
