(ns re-frame-resume.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-resume.styles :as styles]
   [re-frame-resume.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      {:class (styles/level1)}
      "Hello from " @name]
     ]))
