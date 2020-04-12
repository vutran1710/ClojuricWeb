(defproject webapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [http-kit "2.3.0"]
                 [metosin/reitit "0.4.2"]
                 [com.taoensso/timbre "4.10.0"]
                 [ring/ring-devel "1.8.0"]
                 [aero "1.1.6"]]
  :main webapp.core
  :repl-options {:init-ns webapp.core}
  :profiles {:user
             {:plugins [[lein-kibit "0.1.8"]
                        [jonase/eastwood "0.3.10"]]}})
