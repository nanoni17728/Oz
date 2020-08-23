;;; Copyright © 2020 Nanoni
;;; This program is a free software published under the AGPLv3 or later.
;;; It is distributed without any warranty.
;;; You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.

(ns utils.build
  (:require [clojure.java.io :as io]))

(defn copy-resources!
  {:shadow.build/stage :compile-finish}
  [build-state & args]
  (io/copy (io/file "src/resources/index.html") (io/file "public/index.html"))
  build-state)