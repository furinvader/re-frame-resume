(ns app.views
  (:require ["@mui/material/AppBar" :default AppBar]
            ["@mui/material/Box" :default Box]
            ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            ["@mui/material/Skeleton" :default Skeleton]
            ["@mui/material/Toolbar" :default Toolbar]
            ["@mui/material/Typography" :default Typography]
            [app.components.markdown :as md]
            [app.routing.views :as routing]
            [app.subs :as subs]
            [re-frame.core :as rf]))

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

(defn header []
  [:<>
   [md-elements @(rf/subscribe [::subs/contents-by-position "header"])]
   (when @(rf/subscribe [::subs/loading?])
     [:<>
      [:> Typography {:variant "h1"} [:> Skeleton {:width "40%"}]]
      [:> Typography {:variant "h2"} [:> Skeleton {:width "50%"}]]])])

(defn main []
  [:<>
   [md-elements @(rf/subscribe [::subs/contents-by-position "main"])]
   (when @(rf/subscribe [::subs/loading?])
     [:<>
      [:> Typography {:variant "h1"} [:> Skeleton {:width "50%"}]]
      [:> Typography {:variant "body1"} [:> Skeleton]]
      [:> Typography {:variant "body1"} [:> Skeleton]]
      [:> Typography {:variant "body1"} [:> Skeleton]]
      [:> Typography {:variant "body1"} [:> Skeleton]]
      [:> Typography {:variant "body1"} [:> Skeleton]]
      [:> Typography {:variant "body1"} [:> Skeleton {:width "30%"}]]])])

(defn side []
  [:<>
   [md-elements @(rf/subscribe [::subs/contents-by-position "side"])]
   (when @(rf/subscribe [::subs/loading?])
     [:> Skeleton {:variant "rectangular" :height 100}])])

(defn footer []
  [:<>
   [md-elements @(rf/subscribe [::subs/contents-by-position "footer"])]
   (when @(rf/subscribe [::subs/loading?])
     [:> Typography {:variant "body2"} [:> Skeleton]])])

(defn app-page []
  [:> Box {:sx
           {:bgcolor "grey.300"
            :minHeight "100vh"}}
   [:> Container {:maxWidth "lg"}
    [:> AppBar {:color "transparent"
                :elevation 0
                :position "static"}
     [:> Toolbar [routing/main-nav]]]
    [:> Grid {:container true :spacing {:xs 2}}
     [:> Grid {:item true :xs 1} [image]]
     [:> Grid {:item true :xs 11} [header]]
     [:> Grid {:item true :xs 1} [side]]
     [:> Grid {:item true :xs 11} [main]]
     [:> Grid {:item true :xs 12} [footer]]]]])

(defn app []
  [routing/page-router [app-page]])
