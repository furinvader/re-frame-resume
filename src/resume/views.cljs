(ns resume.views
  (:require
   [re-frame.core :as re-frame]
   [resume.styles :as styles]
   [resume.subs :as subs]
   [resume.events :as events]))

(defn hello-world []
  (let [text (re-frame/subscribe [::subs/hello-world])]
    [:div @text 
     [:button {:on-click #(re-frame/dispatch [::events/hello-world])} "request"]]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [hello-world]
     [:h1
      {:class (styles/level1)}
      "Hello from " @name]]))
