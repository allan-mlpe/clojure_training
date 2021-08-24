(ns mutabilidade-com-atomos-e-refs.core
  (:use [clojure pprint])
  (:require [mutabilidade-com-atomos-e-refs.model :as h.model]))



(pprint (h.model/novo-hospital))
(pprint h.model/fila-vazia)