(ns content.views
  (:require ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            [content.subs :as subs]
            [re-frame.core :as rf]
            ["react-markdown" :default ReactMarkdown]))

(defn layout [children]
  [:> Container {:maxWidth "md"}
   [:> Grid {:container true :spacing {:xs 2}}
    [:> Grid {:item true :xs 1}
     [:img {:src "img/bunnies.svg" :alt "🐇🐇" :width "100%"}]]
    [:> Grid {:item true :xs 11} "person"]
    [:> Grid {:item true :xs 1} "overview"]
    [:> Grid {:item true :xs 11} "content" children]]])

(defn markdown [md]
  [:> ReactMarkdown md])

(defn content-renderer []
  (let [elements @(rf/subscribe [::subs/elements])]
    [layout
     (for [{id :id md :markdown} elements]
       ^{:key id} [markdown md])]))
