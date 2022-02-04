(ns app.routing.views
  (:require [app.routing.events :as events]
            [re-frame.core :as rf]
            ["react" :as react]
            ["react-router-dom" :as router]))

(defn fc-path-changed [children]
  (let [location (router/useLocation)
        path (.-pathname location)]
    (react/useEffect
     #(rf/dispatch [::events/path-changed path])
     #js[path])
    children))

(defn path-changed [children]
  [:f> fc-path-changed children])
