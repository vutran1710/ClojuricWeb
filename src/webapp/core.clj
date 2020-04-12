(ns webapp.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [ring.middleware.params :as params]
            [reitit.ring :as ring]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [reitit.ring.coercion :as coercion]
            [aero.core :refer (read-config)]
            [taoensso.timbre :as timbre])
  (:require [webapp.routes :as r]))

(def config
  (read-config "config.edn" {:profile :dev}))

(def app
  (ring/ring-handler
   (ring/router
    [r/routes]
    {:data {:muuntaja   m/instance
            :middleware [params/wrap-params
                         muuntaja/format-middleware
                         coercion/coerce-exceptions-middleware
                         coercion/coerce-request-middleware
                         coercion/coerce-response-middleware]}})
   (ring/create-default-handler)))

(defn -main
  [& args]
  (let [server-cfg (:webserver config)]
    (server/run-server #'app server-cfg)
    (println (str "Running webserver at http:/127.0.0.1:" (:port server-cfg) "/"))))
