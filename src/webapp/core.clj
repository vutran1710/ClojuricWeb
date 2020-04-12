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
  (:require [webapp.routes.simple :as simple-route]
            [webapp.helper :refer (load-config)]))

(def config (load-config))
(def server-cfg (:webserver config))

(def app
  (ring/ring-handler
   (ring/router
    [simple-route/routes]
    {:data {:muuntaja   m/instance
            :middleware [params/wrap-params
                         muuntaja/format-middleware
                         coercion/coerce-exceptions-middleware
                         coercion/coerce-request-middleware
                         coercion/coerce-response-middleware]}})
   (ring/create-default-handler)))

(defn -main
  [& args]
  (server/run-server #'app server-cfg)
  (timbre/info (str "Running webserver at http:/127.0.0.1:" (:port server-cfg) "/")))
