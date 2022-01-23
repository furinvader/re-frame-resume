(ns app.views
  (:require [content.views]
            [app.events :as events]
            [re-frame.core :as rf]))

(defn view-ready []
  (rf/dispatch [::events/view-ready])
  (fn [] nil))

(defn main-panel []
  [:<>
   [content.views/content-renderer]
   [view-ready]])
