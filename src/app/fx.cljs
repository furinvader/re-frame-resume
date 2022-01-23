(ns app.fx
  (:require [re-frame.core :as rf]
            [app.styles :as styles]))

(rf/reg-fx
 ::remove-preloader
 (fn []
   (let [preloader (js/document.getElementById "preloader")]
     (.classList.add preloader (styles/preloader-done)))))
