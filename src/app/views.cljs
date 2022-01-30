(ns app.views
  (:require [app.content.views :as content.views]))

(defn app []
  [content.views/content-renderer])
