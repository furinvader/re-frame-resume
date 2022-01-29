(ns app.views
  (:require [app.content.views :as content.views]))

(defn main-panel []
  [content.views/content-renderer])
