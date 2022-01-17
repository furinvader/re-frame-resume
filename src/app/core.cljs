(ns app.core
  (:require
   [reagent.dom :as rdom]
   [day8.re-frame.http-fx]
   [re-frame.core :as rf]
   [app.events :as events]
   [app.views :as views]
   [app.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))