(ns clojisr.v1.applications.plotting
  (:require [clojisr.v1.r :refer [r]]
            [clojisr.v1.require :refer [require-r]]
            [cambium.core :as log])
  (:import [java.io File]))

(try
  (require-r '[grDevices :refer [svg png dev-off]])
  (catch Exception e
    (log/warn "Failed to load grDevices package.")))

(try
  (require-r '[ggplot2 :refer [ggsave]])
  (catch Exception e
    (log/warn "Failed to load ggplot2 package.")))

(defn plotting-function->svg [plotting-function]
  (let [tempfile (File/createTempFile "ggplot" ".svg")
        path     (.getPath tempfile)
        _        (svg :filename path)
        _        (plotting-function)
        _        (dev-off)
        result   (slurp path)]
    (.delete tempfile)
    result))

(defn ggplot->svg [ggplot-r-object]
  (let [tempfile (File/createTempFile "ggplot" ".svg")
        _        (r ['ggsave
                     ggplot-r-object
                     :filename (.getPath tempfile)])
        svg      (slurp tempfile)]
    (.delete tempfile)
    svg))
