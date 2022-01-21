(ns content.views
  (:require ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            [content.markdown :as md]
            [content.subs :as subs]
            [re-frame.core :as rf]))

(defn layout [children]
  [:> Container {:maxWidth "md"}
   [:> Grid {:container true :spacing {:xs 2}}
    [:> Grid {:item true :xs 1}
     [:img {:src "img/bunnies.svg" :alt "🐇🐇" :width "100%"}]]
    [:> Grid {:item true :xs 11} "person"]
    [:> Grid {:item true :xs 1} "overview"]
    [:> Grid {:item true :xs 11} "content" children]]])

(defn content-renderer []
  (let [elements @(rf/subscribe [::subs/elements])]
    [layout
     (for [{:keys [id text]} elements]
       ^{:key id} [md/markdown text])]))
