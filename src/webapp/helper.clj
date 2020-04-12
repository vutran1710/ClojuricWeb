(ns webapp.helper
  (:require
   [aero.core :refer (read-config)]))

(def env-definitions
  {:development "dev"
   :production  "prod"
   :staging     "stg"
   :test        "test"})

(def env
  (get (System/getenv) "CLJ_ENV" "development"))

(def is-dev?
  (= env "development"))

(defn load-config []
  (let [profile ((keyword env) env-definitions)]
    (read-config "config.edn"
                 {:profile (keyword profile)})))
