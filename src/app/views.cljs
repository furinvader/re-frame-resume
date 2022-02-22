(ns app.views
  (:require ["@mui/material/AppBar" :default AppBar]
            ["@mui/material/Box" :default Box]
            ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            ["@mui/material/Paper" :default Paper]
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

(defn main []
  [:<>
   [md-elements @(rf/subscribe [::subs/contents-by-position "main"])]
   (when @(rf/subscribe [::subs/loading?])
     [:<>
      [:> Typography {:variant "h2"} [:> Skeleton {:width "50%"}]]
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
     [:> Skeleton {:variant "rectangular" :height 300}])])

(defn footer []
  [:<>
   [md-elements @(rf/subscribe [::subs/contents-by-position "footer"])]
   (when @(rf/subscribe [::subs/loading?])
     [:> Typography {:variant "body2"} [:> Skeleton]])])

(defn page-subtitle [{:keys [title subtitle]}]
  (if (empty? subtitle) title subtitle))

(defn app-page [page]
  [:> Box {:sx {:bgcolor "grey.300"
                :minHeight "100vh"}}
   [:> Container {:maxWidth "lg"}
    [:> AppBar {:color "transparent"
                :elevation 0
                :position "static"}
     [:> Toolbar
      [:> Grid {:container true
                :spacing {:xs 2}
                :sx {:justifyContent "space-between"}}
       [:> Grid {:item true :xs 4} [image]]
       [:> Grid {:item true
                 :xs 8
                 :sx {:display "flex"
                      :alignSelf "center"
                      :justifyContent "flex-end"}}
        [routing/main-nav]]]]]]
   [:> Box {:sx {:bgcolor "grey.200"
                 :mt 4}}
    [:> Container {:maxWidth "lg"}
     [:> Grid {:container true
               :spacing {:xs 4}
               :rowSpacing {:xs 0}}
      [:> Grid {:item true
                :xs 4
                :sx {:position "relative"}}
       [:> Box {:sx {:width "100%"
                     :position "absolute"}}
        [:> Paper {:sx {:p 1 :mx 4 :mt -3}}
         [side]]]]
      [:> Grid {:item true :xs 8 :sx {:py 1}}
       [:> Typography {:variant "h1"
                       :sx {:py 1
                            :fontSize "3rem"}}
        [page-subtitle page]]]]]]
   [:> Container {:maxWidth "lg" :sx {:pt 2}}
    [:> Grid {:container true :spacing {:xs 4}}
     [:> Grid {:item true :xs 4}]
     [:> Grid {:item true :xs 8}
      [main]]
     [:> Grid {:item true :xs 12}
      [footer]]]]])

(defn app []
  [routing/page-router app-page])
