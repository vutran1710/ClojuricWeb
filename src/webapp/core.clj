(ns webapp.core  ;(1)
  (:gen-class)
  (:require [org.httpkit.server :as server])) ;(2)

(defn baby-small-app [req] ;(3)
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Pew pew!"})

(defn -main ;(4)
  "This is our app's entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))] ;(5)
    (server/run-server #'baby-small-app {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
