(ns app.views
  (:require ["@mui/material/AppBar" :default AppBar]
            ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            ["@mui/material/Skeleton" :default Skeleton]
            ["@mui/material/Toolbar" :default Toolbar]
            ["@mui/material/Link" :default Link]
            ["@mui/material/Button" :default Button]
            ["@mui/material/Typography" :default Typography]
            [app.components.markdown :as md]
            [app.subs :as subs]
            [re-frame.core :as rf]
            [reagent.core :as r]
            ["react-router-dom" :refer (HashRouter Routes Route Link)
             :rename {Link RouteLink}]))

(defn md-elements [elements]
  [:<>
   (for [{:keys [id text]} elements]
     ^{:key id} [md/mui text])])

(defn image []
  [:div
   {:style {:width "100%"
            :height "100%"}
    :class "bunnify"}])

(defn header []
  (let [elements @(rf/subscribe [::subs/elements "header"])
        loading? @(rf/subscribe [::subs/loading? "header"])]
    (if-not loading?
      [md-elements elements]
      [:<>
       [:> Typography {:variant "h1"} [:> Skeleton {:width "40%"}]]
       [:> Typography {:variant "h2"} [:> Skeleton {:width "50%"}]]])))

(defn side []
  (let [elements @(rf/subscribe [::subs/elements "side"])
        loading? @(rf/subscribe [::subs/loading? "side"])]
    (if-not loading?
      [md-elements elements]
      [:<>
       [:> Skeleton {:variant "rectangular" :height 100}]])))

(defn main []
  (let [elements @(rf/subscribe [::subs/elements "main"])
        loading? @(rf/subscribe [::subs/loading? "main"])]
    (if-not loading?
      [md-elements elements]
      [:<>
       [:> Typography {:variant "h1"} [:> Skeleton {:width "50%"}]]
       [:> Typography {:variant "body1"} [:> Skeleton]]
       [:> Typography {:variant "body1"} [:> Skeleton]]
       [:> Typography {:variant "body1"} [:> Skeleton]]
       [:> Typography {:variant "body1"} [:> Skeleton]]
       [:> Typography {:variant "body1"} [:> Skeleton]]
       [:> Typography {:variant "body1"} [:> Skeleton {:width "30%"}]]])))

(defn footer []
  (let [elements @(rf/subscribe [::subs/elements "footer"])
        loading? @(rf/subscribe [::subs/loading? "footer"])]
    (if-not loading?
      [md-elements elements]
      [:> Typography {:variant "body2"} [:> Skeleton]])))

(defn navigation []
  (let [items @(rf/subscribe [::subs/navigation])]
    [:> AppBar {:position "static"}
     [:> Toolbar
      (for [{:keys [id path nav]} items]
        ^{:key id} [:> Button
                    [:> Link
                     {:underline "none"
                      :color "white"
                      :component RouteLink
                      :to path}
                     nav]])]]))

(defn page []
  [:> Container {:maxWidth "lg"}
   [navigation]
   [:> Grid {:container true :spacing {:xs 2}}
    [:> Grid {:item true :xs 1} [image]]
    [:> Grid {:item true :xs 11} [header]]
    [:> Grid {:item true :xs 1} [side]]
    [:> Grid {:item true :xs 11} [main]]
    [:> Grid {:item true :xs 12} [footer]]]])

(defn app []
  [:> HashRouter
   [:> Routes
    [:> Route {:path "/" :element (r/as-element [page])}]]])
