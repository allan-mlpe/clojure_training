(ns mutabilidade-com-atomos-e-refs.aula1
  (:use [clojure pprint])
  (:require [mutabilidade-com-atomos-e-refs.model :as h.model]
            [mutabilidade-com-atomos-e-refs.logic :as h.logic]))

(defn simula-um-dia []
  ; root binding
  (def hospital (h.model/novo-hospital))
  (def hospital (h.logic/chega-em hospital :espera "111"))
  (def hospital (h.logic/chega-em hospital :espera "222"))
  (def hospital (h.logic/chega-em hospital :espera "333"))

  (def hospital (h.logic/chega-em hospital :laboratorio1 "444"))
  (def hospital (h.logic/chega-em hospital :laboratorio3 "555"))
  (println "======== Hospital antes do antedimento começar:")
  (pprint hospital)
  (def hospital (h.logic/atende hospital :laboratorio3))
  (def hospital (h.logic/atende hospital :espera))
  (def hospital (h.logic/atende hospital :espera))

  (println "======== Hospital após alguns atendimentos:")
  (pprint hospital))

(simula-um-dia)