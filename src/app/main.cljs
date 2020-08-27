;;; Copyright © 2020 Nanoni
;;; This program is a free software published under the AGPLv3 or later.
;;; It is distributed without any warranty.
;;; You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.

(ns app.main
  (:require [goog.events :as events]
            [goog.dom :as dom]))

(enable-console-print!)

(defonce filter-amount (atom 1.0))
(defonce canvas (dom/getElement "picture"))
(defonce ctx (.getContext canvas "2d"))
(defonce img (js/Image.))
(defonce slider (dom/getElement "slider"))
(defonce original (js/ImageData. 1 1))
(declare clj-data)

(defn multiply [data]
  (map (partial unchecked-multiply @filter-amount) data))

(defn processing-image
  []
  (let [width  (/ (.-width img) 2)
        height (/ (.-height img) 2)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)
    (.drawImage ctx img 0 0 width height)
    (set! original (.getImageData ctx 0 0 width height))
    (set! clj-data (vec (js->clj (.-data original))))))

(defn apply-filter-amount []
  (.putImageData ctx
    (js/ImageData. (.from js/Uint8ClampedArray (clj->js (multiply clj-data))) (.-width original)) 0 0))

(add-watch filter-amount :apply apply-filter-amount)

(defn -main!
  []
  (events/listen js/document "DOMContentLoaded"
    (fn []
      (events/listen img "load" processing-image)
      (set! (.-src img) "parrot.jpg")
      (set! (.-onchange slider) (fn [e] (reset! filter-amount (.. e -target -value))))
      (js/console.log "Hello World! This is a test"))))
