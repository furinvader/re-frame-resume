(ns content.views
  (:require ["@mui/material/Container" :default Container]
            ["@mui/material/Grid" :default Grid]
            [content.markdown :as md]
            [content.subs :as subs]
            [re-frame.core :as rf]))

(defn layout [image header main side footer]
  [:> Container {:maxWidth "lg"}
   [:> Grid {:container true :spacing {:xs 2}}
    [:> Grid {:item true :xs 1}
     [:img {:src "img/bunnies.svg" :alt "🐇🐇" :width "100%"}]
     image]
    [:> Grid {:item true :xs 11} header]
    [:> Grid {:item true :xs 1} side]
    [:> Grid {:item true :xs 11} main]
    [:> Grid {:item true :xs 12} footer]]])

(defn md-elements [elements]
  [:<>
   (for [{:keys [id text]} elements]
     ^{:key id} [md/markdown text])])

(defn content-renderer []
  (let [image @(rf/subscribe [::subs/elements "image"])
        header @(rf/subscribe [::subs/elements "header"])
        main @(rf/subscribe [::subs/elements "main"])
        side @(rf/subscribe [::subs/elements "side"])
        footer @(rf/subscribe [::subs/elements "footer"])]
    [layout
     [md-elements image]
     [md-elements header]
     [md-elements main]
     [md-elements side]
     [md-elements footer]]))
