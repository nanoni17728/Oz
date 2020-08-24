;;; Copyright © 2020 Nanoni
;;; This program is a free software published under the AGPLv3 or later.
;;; It is distributed without any warranty.
;;; You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.

(ns app.main
  (:require [rum.core :as rum]
            [utils.Uint8ClampedArray :as u8]))

(enable-console-print!)

(def amount (atom 1.0))
(def canvas (.getElementById js/document "picture"))
(def ctx (.getContext canvas "2d"))
(def img (js/Image.))

(defn after-load
  []
  (let [width  (/ (.-width img) 2)
        height (/ (.-height img) 2)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)
    (def original (.getImageData ctx 0 0 width height))
    (.drawImage ctx img 0 0 width height)
    (.putImageData ctx 
                  (js/ImageData. (u8/fill (.-data original) (* 255 @amount)) (.-width original)) 0 0)))
(.addEventListener img "load" after-load)
(set! (.-src img) "parrot.jpg")

(defn test-watcher
  [key watched old-state new-state]
  (.putImageData ctx
                 (js/ImageData. (u8/fill (.-data original) (* 100 new-state)) (.-width original)) 0 0)
  #(js/console.log "key:" (str key) "watched:" (str watched) "old-state:" (str old-state) "new-state:" (str new-state)))
(add-watch amount :watcher test-watcher)

(rum/defc slider []
  [:input {:type "range" :min 0.0 :max 1.0 :step 0.01 :defaultValue (str @amount) :onChange (fn [e] (reset! amount (.. e -target -value)))}])

(defn main!
  []
  (rum/mount (slider) (.getElementById js/document "hook"))
  (js/console.log "Hello World! This is a test"))
