(ns content.views
  (:require ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            ["@mui/material/Skeleton" :default Skeleton]
            ["@mui/material/Typography" :default Typography]
            [content.markdown :as md]
            [content.subs :as subs]
            [re-frame.core :as rf]))

(defn md-elements [elements]
  [:<>
   (for [{:keys [id text]} elements]
     ^{:key id} [md/mui text])])

(defn image-content []
  [:div
   {:style {:width "100%"
            :height "100%"}
    :class "bunnify"}])

(defn header-content []
  (let [elements @(rf/subscribe [::subs/elements "header"])
        loading? @(rf/subscribe [::subs/loading? "header"])]
    (if-not loading?
      [md-elements elements]
      [:<>
       [:> Typography {:variant "h1"} [:> Skeleton {:width "40%"}]]
       [:> Typography {:variant "h2"} [:> Skeleton {:width "50%"}]]])))

(defn side-content []
  (let [elements @(rf/subscribe [::subs/elements "side"])
        loading? @(rf/subscribe [::subs/loading? "side"])]
    (if-not loading?
      [md-elements elements]
      [:<>
       [:> Skeleton {:variant "rectangular" :height 100}]])))

(defn main-content []
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

(defn footer-content []
  (let [elements @(rf/subscribe [::subs/elements "footer"])
        loading? @(rf/subscribe [::subs/loading? "footer"])]
    (if-not loading?
      [md-elements elements]
      [:> Typography {:variant "body2"} [:> Skeleton]])))

(defn content-renderer []
  [:> Container {:maxWidth "lg"}
   [:> Grid {:container true :spacing {:xs 2}}
    [:> Grid {:item true :xs 1} [image-content]]
    [:> Grid {:item true :xs 11} [header-content]]
    [:> Grid {:item true :xs 1} [side-content]]
    [:> Grid {:item true :xs 11} [main-content]]
    [:> Grid {:item true :xs 12} [footer-content]]]])
