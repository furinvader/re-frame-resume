(ns app.title.fx
  (:require [re-frame.core :as rf]))

(rf/reg-fx
 ::title
 (fn [title]
   (set! (.-title js/document) title)))
