(ns resume.core
  (:require
   [reagent.dom :as rdom]
   [day8.re-frame.http-fx]
   [re-frame.core :as re-frame]
   [resume.events :as events]
   [resume.views :as views]
   [resume.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
