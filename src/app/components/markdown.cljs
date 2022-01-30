(ns app.components.markdown
  (:require ["@mui/material/Typography" :default Typography]
            ["react-markdown" :default ReactMarkdown]
            [reagent.core :as r]))

(defn reactify [comp-map]
  (into {} (map #(update % 1 r/reactify-component) comp-map)))

(defn markdown
  ([md]
   [markdown {} md])
  ([components md]
   [:> ReactMarkdown {:components (reactify components)} md]))

(defn h1 [props]
  [:> Typography (merge props {:variant "h1"})])

(defn h2 [props]
  [:> Typography (merge props {:variant "h2"})])

(defn p [props]
  [:> Typography (merge props {:variant "body1"})])

(def mui-defaults {:h1 h1 :h2 h2 :p p})

(defn mui
  ([md]
   [markdown mui-defaults md])
  ([components md]
   [markdown (merge mui-defaults components) md]))
