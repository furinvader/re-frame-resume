(ns app.routing.views
  (:require ["@mui/material/Button" :default Button]
            ["@mui/material/Link" :default Link]
            [app.routing.events :as events]
            [app.routing.subs :as subs]
            [re-frame.core :as rf]
            ["react" :as react]
            ["react-router-dom" :as router]
            [reagent.core :as r]))

(defn fc-path-changed [children]
  (let [location (router/useLocation)
        path (.-pathname location)]
    (react/useEffect
     #(rf/dispatch [::events/path-changed path])
     #js[path])
    children))

(defn path-changed [children]
  [:f> fc-path-changed children])

(defn page-router [component]
  (let [routes @(rf/subscribe [::subs/pages])]
    [:> router/BrowserRouter
     [:> router/Routes
      (for [route routes]
        [:> router/Route {:key (:id route)
                          :path (:path route)
                          :element (r/as-element [path-changed component])}])]]))

(defn main-nav []
  (let [pages @(rf/subscribe [::subs/pages])]
    [:<>
     (for [{:keys [id path title nav]} pages]
       [:> Button {:key id}
        [:> Link
         {:underline "none"
          :color "white"
          :component router/Link
          :to path}
         (if (empty? nav) title nav)]])]))
