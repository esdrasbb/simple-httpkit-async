(ns simple-httpkit-async.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))

(def db {:classname "org.sqlite.JDBC",
  :subprotocol
  "sqlite",
  :subname
  "db.sq3"})

(defn create-guestbook-table []
  (sql/with-connection
  db
  (sql/create-table
  :user
  [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
  [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
  [:name "TEXT"]
  [:email "TEXT"])
  (sql/do-commands "CREATE INDEX timestamp_index ON user (name)")))

(defn read-users []
  (sql/with-connection
  db
  (sql/with-query-results res
  ["SELECT * FROM user ORDER BY timestamp DESC"]
  (doall res))))

(defn save-user [name email]
  (sql/with-connection
  db
  (sql/insert-values
  :user
  [:name :email :timestamp]
  [name email (new java.util.Date)])))
