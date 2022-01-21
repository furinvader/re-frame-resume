(ns content.markdown
  (:require ["@mui/material/Typography" :default Typography]
            ["react-markdown" :default ReactMarkdown]
            [reagent.core :as r]))

(defn h1 [props]
  [:> Typography (merge props {:variant "h1"})])

(defn h2 [props]
  [:> Typography (merge props {:variant "h2"})])

(defn body [props]
  [:> Typography (merge props {:variant "body1"})])

(def default-components
  {:h1 (r/reactify-component h1)
   :h2 (r/reactify-component h2)
   :p (r/reactify-component body)})

(defn markdown [text]
  [:> ReactMarkdown {:components default-components} text])
