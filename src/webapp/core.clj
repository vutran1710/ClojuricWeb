(ns webapp.core  ;(1)
  (:gen-class)
  (:require [org.httpkit.server :as server])
  (:require [aero.core :refer (read-config)])
  (:require
    [taoensso.timbre :as timbre
      :refer [log  trace  debug  info  warn  error  fatal  report
              logf tracef debugf infof warnf errorf fatalf reportf
              spy get-env]]))

(def config
  (read-config "config.edn" {:profile :dev}))

(defn baby-small-app [req] ;(3)
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Pew pew!"})

(defn -main ;(4)
  "This is our app's entry point"
  [& args]
  (let [server-cfg (:webserver config)]
    (info server-cfg)
    (server/run-server #'baby-small-app server-cfg)
    (println (str "Running webserver at http:/127.0.0.1:" (:port server-cfg) "/"))))
