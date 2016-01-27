(ns simple-httpkit-async.bin.main
  (:gen-class)
  (:require [simple-httpkit-async.core :as core]
            [simple-httpkit-async.db :as db]))

(defn -main [& args]
  (println "simple-httpkit-async is starting")
    (if-not (.exists (java.io.File. "./db.sq3"))
      (db/create-guestbook-table))
    (core/go))


;pegar o codigo do pedra-papel-tesoura e colocar em um .clojure
;otimizar para dado um usuário e opção (pedra-papel-tesoura) jogar com o computador
;retornar a resposta
