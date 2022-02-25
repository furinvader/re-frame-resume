(ns app.components.markdown
  (:require ["@mui/material/List" :default List]
            ["@mui/material/ListItem" :default ListItem]
            ["@mui/material/ListItemText" :default ListItemText]
            ["@mui/material/Table" :default Table]
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

(defn h1 [{:keys [children]}]
  [:> Typography {:variant "h1"} children])

(defn h2 [{:keys [children]}]
  [:> Typography {:variant "h2"} children])

(defn h3 [{:keys [children]}]
  [:> Typography {:variant "h3"} children])

(defn p [{:keys [children]}]
  [:> Typography {:variant "body1"} children])

(defn ul [{:keys [children]}]
  [:> List children])

(defn ol [{:keys [children]}]
  [:> List children])

(defn li [{:keys [children]}]
  [:> ListItem
   [:> ListItemText children]])

(defn table [{:keys [children]}]
  [:> TableContainer
   [:> Table children]])

(defn thead [{:keys [children]}]
  [:> TableHead children])

(defn tbody [{:keys [children]}]
  [:> TableBody children])

(defn tr [{:keys [children]}]
  [:> TableRow children])

(defn th [{:keys [children]}]
  [:> TableCell children])

(defn td [{:keys [children]}]
  [:> TableCell children])

(def mui-defaults
  {:h1 h1 :h2 h2 :h3 h3
   :p p :ul ul :ol ol :li li
   :table table :thead thead :tbody tbody :tr tr :th th :td td})

(defn mui
  ([md]
   [markdown mui-defaults md])
  ([components md]
   [markdown (merge mui-defaults components) md]))
