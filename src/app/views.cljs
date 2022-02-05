(ns app.views
  (:require ["@mui/material/AppBar" :default AppBar]
            ["@mui/material/Button" :default Button]
            ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            ["@mui/material/Link" :default Link]
            ["@mui/material/Skeleton" :default Skeleton]
            ["@mui/material/Toolbar" :default Toolbar]
            ["@mui/material/Typography" :default Typography]
            [app.components.markdown :as md]
            [app.routing.views :as routing]
            [app.subs :as subs]
            [re-frame.core :as rf]
            ["react-router-dom" :refer (BrowserRouter Routes Route Link)
             :rename {Link RouteLink}]
            [reagent.core :as r]))

(defn navigation [pages]
  [:> AppBar {:position "static"}
   [:> Toolbar
    (for [{:keys [id path title nav]} pages]
      [:> Button {:key id}
       [:> Link
        {:underline "none"
         :color "white"
         :component RouteLink
         :to path}
        (if (empty? nav) title nav)]])]])

(defn image []
  [:div
   {:style {:width "100%"
            :height "100%"
            :min-height 100}
    :class "bunnify"}])

(defn md-elements [elements]
  [:<>
   (for [{:keys [id text]} elements]
     ^{:key id} [md/mui text])])

(defn header [contents]
  [md-elements contents])

(defn side [contents]
  [md-elements contents])

(defn main [contents]
  [md-elements contents])

(defn footer [contents]
  [md-elements contents])

(defn side-preview []
  [:> Skeleton {:variant "rectangular" :height 100}])

(defn header-preview []
  [:<>
   [:> Typography {:variant "h1"} [:> Skeleton {:width "40%"}]]
   [:> Typography {:variant "h2"} [:> Skeleton {:width "50%"}]]])

(defn main-preview []
  [:<>
   [:> Typography {:variant "h1"} [:> Skeleton {:width "50%"}]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton {:width "30%"}]]])

(defn footer-preview []
  [:> Typography {:variant "body2"} [:> Skeleton]])

(defn app-route []
  (let [pages @(rf/subscribe [::subs/navigation])
        loading? (empty? pages)]
    [:> Container {:maxWidth "lg"}
     [navigation pages]
     [:> Grid {:container true :spacing {:xs 2}}
      [:> Grid {:item true :xs 1} [image]]
      [:> Grid {:item true :xs 11}
       [header @(rf/subscribe [::subs/contents-by-position "header"])
        (when loading? [header-preview])]]
      [:> Grid {:item true :xs 1}
       [side @(rf/subscribe [::subs/contents-by-position "side"])]
       (when loading? [side-preview])]
      [:> Grid {:item true :xs 11}
       [main @(rf/subscribe [::subs/contents-by-position "main"])]
       (when loading? [main-preview])]
      [:> Grid {:item true :xs 12}
       [footer @(rf/subscribe [::subs/contents-by-position "footer"])]
       (when loading? [footer-preview])]]]))

(defn app []
  [:> BrowserRouter
   [:> Routes
    [:> Route {:path "*"
               :element (r/as-element [routing/path-changed [app-route]])}]]])
