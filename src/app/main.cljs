;;; Copyright © 2020 Nanoni
;;; This program is a free software published under the AGPLv3 or later.
;;; It is distributed without any warranty.
;;; You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.

(ns app.main
  (:require [rum.core :as rum]))

(enable-console-print!)

(def red-percent (atom 100))
(def canvas (.getElementById js/document "picture"))
(def ctx (.getContext canvas "2d"))
(def img (js/Image.))
(.addEventListener img "load"
                   (fn []
                     (let [width  (/ (.-width img) 2)
                           height (/ (.-height img) 2)]
                       (set! (.-width canvas) width)
                       (set! (.-height canvas) height)
                     (.drawImage ctx img 0 0 width height))))
(set! (.-src img) "parrot.jpg")

(rum/defc slider []
  [:input {:type "range" :min 0 :max 100 :defaultValue "100" :onChange (fn [e] (reset! red-percent (.. e -target -value)))}])

(defn main!
  []
  (rum/mount (slider) (.getElementById js/document "hook"))
  (js/console.log "Hello World! This is a test"))
