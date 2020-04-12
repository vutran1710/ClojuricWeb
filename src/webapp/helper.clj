(ns webapp.helper
  (:require
   [aero.core :refer (read-config)]
   [taoensso.timbre :as timbre]))

(def env-definitions
  {:development "dev"
   :production  "prod"
   :staging     "stg"
   :test        "test"})

(defn load-config []
  (let [env     (get (System/getenv) "CLJ_ENV" "development")
        profile ((keyword env) env-definitions)
        cfg     (read-config "config.edn" {:profile (keyword profile)})]
    cfg))
