(defproject simple-httpkit-async "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                  [http-kit "2.1.18"]
                  [compojure "1.1.5"]
                  [org.clojure/data.json "0.2.6"]
                  ;;JDBC dependencies
                  [org.clojure/java.jdbc "0.2.3"]
                  [org.xerial/sqlite-jdbc "3.7.2"]
                  ;;Async
                  [org.clojure/core.async "0.2.374"]
                  ]

  :main ^:skip-aot simple-httpkit-async.bin.main)
