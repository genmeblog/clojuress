(ns clojisr.v1.protocols)

(defprotocol Session
  (close [session])
  (closed? [session])
  (id [session])
  (session-args [session])
  (desc [session])
  (eval-r->java [session code])
  (java->r-set [session varname java-object])
  (java->specified-type [session java-object type])
  (java->naive-clj [session java-object])
  (java->clj [session java-object])
  (clj->java [session clj-object])
  (print-to-string [session r-obj])
  (package-symbol->r-symbol-names [session package-symbol]))


