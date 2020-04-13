(ns webapp.routes.simple
  (:require [org.httpkit.server :as server]
            [reitit.ring :as ring]
            [aero.core :refer (read-config)]))

(def routes
  ["/clojure"
   ["/plus" {:get  (fn [{{:strs [x y]} :query-params :as req}]
                     (let [result   (+ (Long/parseLong x) (Long/parseLong y))
                           response {:total result}]
                       {:status 200
                        :body   response}))
             :post (fn [{{:keys [x y]} :body-params}]
                     (let [result   (+ x y)
                           response {:total result}]
                       {:status 200
                        :body   response}))}]])
