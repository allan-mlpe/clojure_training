(ns mutabilidade-com-atomos-e-refs.aula6
  (:require [mutabilidade-com-atomos-e-refs.model :as h.model])
  (:use [clojure pprint]))


(defn chega-em [fila pessoa]
  (conj fila pessoa))

(defn chega-em! [hospital pessoa]
  (let [fila (get hospital :espera)]
   (ref-set fila (chega-em @fila pessoa))))                 ; precisamos usar o atalho para `deref` em fila, uma vez que `chega-em` é uma função pura e trabalha com uma fila e não com a referência

(defn chega-em-com-alter! [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))                          ; com o `alter` não precisamos fazer a "dereferência" e também a sintaxe é bem parecida com a `swap!`: recebe a função a ser executada e o resto dos parâmetros (uma vez que a ref é passada como o primeiro parâmetro)

(defn simula-um-dia []
  (let [hospital {:espera (ref h.model/fila-vazia)
                  :laboratorio1 (ref h.model/fila-vazia)
                  :laboratorio2 (ref h.model/fila-vazia)
                  :laboratorio3 (ref h.model/fila-vazia)}]

    (dosync (chega-em-com-alter! hospital "joca"))                    ; usamos dosync para abrir a transação e assim poder alterar o valor das `ref` das filas do hospital
    (pprint hospital)
    )
  )

(simula-um-dia)