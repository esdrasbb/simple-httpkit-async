(ns simple-httpkit-async.server
  (:require [org.httpkit.server :refer [run-server]]
            [compojure.core :refer [defroutes context routes GET POST]]
            [compojure.route :as route]
            [clojure.data.json :as json]))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn show-landing-page [amount]
  (str "chegou no landing " amount))

(defroutes all-routes
  (GET "/" [] (json/write-str {:body "iaeok"}))
  (GET "/user/:amount" [amount] (show-landing-page [amount]))
  (route/not-found "Not Found")) ;; all other, return 404

(defn start-server [config]
  ;; The #' is useful when you want to hot-reload code
  ;; You may want to take a look: https://github.com/clojure/tools.namespace
  ;; and http://http-kit.org/migration.html#reload
  (let [{:keys [port]} config]
    (println ";; Started HTTP-Kit server on port" port)
    (reset! server (run-server all-routes {:port port}))))
