(ns app.core
  (:require [app.config :as config]
            [app.events :as events]
            [app.views :as views]
            [re-frame.core :as rf]
            [reagent.dom :as rdom]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/app] root-el #(rf/dispatch [::events/view-ready]))))

(defn init []
  (rf/dispatch-sync [::events/initialize])
  (dev-setup)
  (mount-root))
