(ns simple-httpkit-async.bin.main
  (:gen-class)
  (:require [simple-httpkit-async.core :as core]))

(defn -main [& args]
    (core/go))
