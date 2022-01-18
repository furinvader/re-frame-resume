(ns content.views
  (:require [content.subs :as subs]
            [re-frame.core :as rf]))

(defn content-renderer []
  (let [elements @(rf/subscribe [::subs/elements])]
    [:div "content: "
     (for [{:keys [id markdown]} elements]
       ^{:key id} [:pre markdown])]))
