(ns app.styles
  (:require [spade.core   :refer [defclass defglobal]]))

(defglobal defaults
  [:body {:margin 0 :padding 0}
   :#preloader {:transition "opacity 1s"}])

(defclass preloader-done []
  {:opacity 0})
