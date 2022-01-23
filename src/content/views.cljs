(ns content.views
  (:require ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            ["@mui/material/Skeleton" :default Skeleton]
            ["@mui/material/Typography" :default Typography]
            [content.markdown :as md]
            [content.subs :as subs]
            [re-frame.core :as rf]))

(defn grid-layout [image header main side footer]
  [:> Container {:maxWidth "lg"}
   [:> Grid {:container true :spacing {:xs 2}}
    [:> Grid {:item true :xs 1}
     [:div
      {:style {:width "100%"
               :height "100%"}
       :class "bunnify"}]
     image]
    [:> Grid {:item true :xs 11} header]
    [:> Grid {:item true :xs 1} side]
    [:> Grid {:item true :xs 11} main]
    [:> Grid {:item true :xs 12} footer]]])

(defn md-elements [elements]
  [:<>
   (for [{:keys [id text]} elements]
     ^{:key id} [md/markdown text])])

(defn header-skeleton []
  [:<>
   [:> Typography {:variant "h1"} [:> Skeleton {:width "40%"}]]
   [:> Typography {:variant "h2"} [:> Skeleton {:width "50%"}]]])

(defn main-skeleton []
  [:<>
   [:> Typography {:variant "h1"} [:> Skeleton {:width "50%"}]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton]]
   [:> Typography {:variant "body1"} [:> Skeleton {:width "30%"}]]])

(defn side-skeleton []
  [:<>
   [:> Skeleton {:variant "rectangular" :height 100}]])

(defn footer-skeleton []
  [:<>
   [:> Typography {:variant "body2"} [:> Skeleton]]])

(defn skeleton-content []
  (let [image ""
        header [header-skeleton]
        main [main-skeleton]
        side [side-skeleton]
        footer [footer-skeleton]]
    [grid-layout image header main side footer]))

(defn loaded-content []
  (let [image [md-elements @(rf/subscribe [::subs/elements "image"])]
        header [md-elements @(rf/subscribe [::subs/elements "header"])]
        main [md-elements @(rf/subscribe [::subs/elements "main"])]
        side [md-elements @(rf/subscribe [::subs/elements "side"])]
        footer [md-elements @(rf/subscribe [::subs/elements "footer"])]]
    [grid-layout image header main side footer]))

(defn content-renderer []
  (let [loading? @(rf/subscribe [::subs/loading?])]
    (if loading?
      [skeleton-content]
      [loaded-content])))
