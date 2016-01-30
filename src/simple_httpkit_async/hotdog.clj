(ns simple-httpkit-async.hotdog
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]]))

(defn hot-dog-machine-v2
  [hot-dog-count]
  (let [in (chan)
        out (chan)]
    (go (loop [hc hot-dog-count]
          (if (> hc 0)
            (let [input (<! in)]
             (if (= 3 input)
                (do (>! out "hot dog")
                    (recur (dec hc)))
                (do (>! out "wilted lettuce")
                    (recur hc))))
           (do (close! in)
                (close! out)))))
    [in out]))


;(let [[in out] (hot-dog-machine-v2 2)]
;  (>!! in "pocket lint")
;  (println (<!! out))
;
;  (>!! in 3)
;  (println (<!! out))
;
;  (>!! in 3)
;  (println (<!! out))
