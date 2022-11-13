(defproject duct-init "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [duct/core "0.8.0"]
                 [duct/module.logging "0.5.0"]
                 [duct/module.sql "0.6.1"]
                 [duct/module.web "0.7.3"]
                 [org.postgresql/postgresql "42.2.19"]
                 [metosin/reitit "0.5.18"]
                 [org.clojure/tools.reader "1.3.6"]]
  :plugins [[duct/lein-duct "0.12.3"]
            [lein-eftest "0.5.9"]]
;;   :eftest {:multithread? :vars
;;            :thread-count 4
;;            :report eftest.report.junit/report
;;          ;; You can optionally write the output to a file like so:
;;            :report-to-file "target/junit.xml"}
  :main ^:skip-aot duct-init.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :middleware     [lein-duct.plugin/middleware]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init (do (require 'dev)
                                   (in-ns 'dev))
                         :init-ns dev}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.3.2"]
                                   [hawk "0.2.11"]
                                   [eftest "0.5.9"]
                                   [kerodon "0.9.1"]]}})
