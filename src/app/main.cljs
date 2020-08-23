;;; Copyright © 2020 Nanoni
;;; This program is a free software published under the AGPLv3 or later.
;;; It is distributed without any warranty.
;;; You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.

(ns app.main
  (:require [rum.core :as rum]))

(rum/defc label
  [text]
  [:div {:class "label"} text])

(defn main!
  []
  (rum/mount (label "test") (.getElementById js/document "hook"))
  (js/console.log "Hello World! This is a test"))