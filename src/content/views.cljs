(ns content.views
  (:require ["@mui/material/Box" :default Box]
            [content.subs :as subs]
            [re-frame.core :as rf]
            ["react-markdown" :default ReactMarkdown]))

(defn markdown [md]
  [:> ReactMarkdown md])

(defn content-renderer []
  (let [elements @(rf/subscribe [::subs/elements])]
    [:> Box "content: "
     (for [{id :id md :markdown} elements]
       ^{:key id} [markdown md])]))
