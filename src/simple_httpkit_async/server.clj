(ns simple-httpkit-async.server
  (:require [org.httpkit.server :refer [run-server]]
            [compojure.core :refer [defroutes context routes GET PUT]]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [simple-httpkit-async.game :as game]
            [simple-httpkit-async.db :as db]))

(defonce server (atom nil))
(def game (game/init))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn play-game [amount]
  (str "executou " (read-string (amount 0)) " vezes. Resultado: "(game/play-many game (read-string (amount 0)))))

(defroutes all-routes
  (GET "/" [] (json/write-str {:body "ok"}))
  (GET "/amount/:amount" [amount] (play-game [amount]))
  (GET "/user/" [] (db/read-users))
  (PUT "/user/:name/" [name] (db/save-user name "null"))
  (PUT "/user/:name/:email" [name email] (db/save-user name email))
  (route/not-found "Not Found")) ;; all other, return 404

(defn start-server [config]
  ;; The #' is useful when you want to hot-reload code
  ;; You may want to take a look: https://github.com/clojure/tools.namespace
  ;; and http://http-kit.org/migration.html#reload
  (let [{:keys [port]} config]
    (println ";; Started HTTP-Kit server on port" port)
    (reset! server (run-server all-routes {:port port}))))
