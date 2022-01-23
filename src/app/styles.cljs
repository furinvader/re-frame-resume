(ns app.styles
  (:require [spade.core   :refer [defclass defglobal]]))

(defglobal preloader
  [:#preloader {:transition "opacity 1s"}])

(defclass preloader-done []
  {:opacity 0})
