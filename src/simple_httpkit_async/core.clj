(ns simple-httpkit-async.core
  (:require [simple-httpkit-async.server :as server]))

(defn go []
  "Starts simple-httpkit-async"
  (server/start-server {:port 8000}))
