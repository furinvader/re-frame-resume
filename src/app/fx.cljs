(ns app.fx
  (:require [app.styles :as styles]
            [re-frame.core :as rf]))

(rf/reg-fx
 ::remove-preloader
 (fn []
   (let [preloader (js/document.getElementById "preloader")]
     (.classList.add preloader (styles/preloader-done)))))

(rf/reg-fx
 ::title
 (fn [title]
   (set! (.-title js/document) title)))
