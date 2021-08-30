(ns mutabilidade-com-atomos-e-refs.aula5
  (:use [clojure pprint])
  (:require [mutabilidade-com-atomos-e-refs.model :as h.model]
            [mutabilidade-com-atomos-e-refs.logic :as h.logic]))

(defn chega-em!
  [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa))

(defn transfere!
  [hospital de para]
  (swap! hospital h.logic/transfere de para))


(defn simula-um-dia []
  (let [hospital (atom (h.model/novo-hospital))]
    (chega-em! hospital "João")
    (chega-em! hospital "Bob")
    (chega-em! hospital "Joãozinho")

    (transfere! hospital :espera :laboratorio1)

    ;(pprint (h.logic/atende-completo @hospital :espera))
    ;(pprint (h.logic/atende-completo-com-juxt @hospital :espera))
    (pprint hospital)
    ))

(simula-um-dia)