(ns app.views
  (:require
   [re-frame.core :as rf]
   [app.styles :as styles]
   [app.subs :as subs]
   [app.events :as events]))

(defn hello-world []
  (let [text (rf/subscribe [::subs/hello-world])]
    [:div @text
     [:button {:on-click #(rf/dispatch [::events/hello-world])} "request"]]))

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]
    [:div
     [hello-world]
     [:h1
      {:class (styles/level1)}
      "Hello from " @name]]))
