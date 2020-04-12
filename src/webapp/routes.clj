(ns webapp.routes
  (:require [org.httpkit.server :as server]
            [reitit.ring :as ring]
            [aero.core :refer (read-config)]
            [taoensso.timbre :refer (info warn)]))

(def routes
  ["/plain"
   ["/plus" {:get (fn [{{:strs [x y]} :query-params :as req}]
                    (info x)
                    (warn y)
                    {:status 200
                     :body {:total (+ (Long/parseLong x) (Long/parseLong y))}})
             :post (fn [{{:keys [x y]} :body-params}]
                     {:status 200
                      :body {:total (+ x y)}})}]])
