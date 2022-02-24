(ns app.components.markdown
  (:require ["@mui/material/Table" :default Table]
            ["@mui/material/TableBody" :default TableBody]
            ["@mui/material/TableCell" :default TableCell]
            ["@mui/material/TableContainer" :default TableContainer]
            ["@mui/material/TableHead" :default TableHead]
            ["@mui/material/TableRow" :default TableRow]
            ["@mui/material/Typography" :default Typography]
            ["react-markdown" :default ReactMarkdown]
            [reagent.core :as r]
            ["remark-gfm" :default remark-gfm]))

(defn reactify [comp-map]
  (into {} (map #(update % 1 r/reactify-component) comp-map)))

(defn markdown
  ([md]
   [markdown {} md])
  ([components md]
   [:> ReactMarkdown {:components (reactify components)
                      :remarkPlugins #js[remark-gfm]} md]))

(defn h1 [props]
  [:> Typography (merge props {:variant "h1"})])

(defn h2 [props]
  [:> Typography (merge props {:variant "h2"})])

(defn h3 [props]
  [:> Typography (merge props {:variant "h3"})])

(defn p [props]
  [:> Typography (merge props {:variant "body1"})])

(defn table [props]
  [:> TableContainer
   [:> Table props]])

(defn thead [props]
  [:> TableHead props])

(defn tbody [props]
  [:> TableBody props])

(defn tr [{:keys [children]}]
  [:> TableRow children])

(defn th [{:keys [children]}]
  [:> TableCell children])

(defn td [{:keys [children]}]
  [:> TableCell children])

(def mui-defaults
  {:h1 h1 :h2 h2 :h3 h3
   :table table :thead thead :tbody tbody :tr tr :th th :td td
   :p p})

(defn mui
  ([md]
   [markdown mui-defaults md])
  ([components md]
   [markdown (merge mui-defaults components) md]))
