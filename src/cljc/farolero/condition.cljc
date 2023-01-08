(ns farolero.condition
  (:require
   [farolero.protocols :refer [ICondition]]
   #?@(:cljs ([goog.string :as gstring]
              [goog.string.format]))))

(defrecord Condition [type msg]
  ICondition
  (handles? [_ v]
    (= type v)))

(defmacro condition [type msg map]
  `(map->Condition
    (merge ~map {:type ~type
                 :msg ~msg
                 :in ~(#?(:clj format
                          :cljs gstring/format)
                       "%s:%s"
                       *ns*
                       (:line (meta &form)))})))
