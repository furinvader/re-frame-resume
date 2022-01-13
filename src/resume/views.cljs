(ns resume.views
  (:require
   [re-frame.core :as re-frame]
   [resume.styles :as styles]
   [resume.subs :as subs]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      {:class (styles/level1)}
      "Hello from " @name]]))
