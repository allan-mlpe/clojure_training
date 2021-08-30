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

;(simula-um-dia)


(defn async-chega-em [hospital pessoa]
  (future                                                   ; a future não retorna uma exception. Em vez disso, ela guarda a exception e segue o fluxo do programa. Se quisermos acessar a exception, devemos armazenar a future e acessar o seu resultado
   (Thread/sleep (rand 5000))
    (dosync
     (println "Tentando adicionar a pessoa:" pessoa)
     (chega-em! hospital pessoa))))


(defn simula-um-dia-async []
  (let [hospital {:espera (ref h.model/fila-vazia)
                  :laboratorio1 (ref h.model/fila-vazia)
                  :laboratorio2 (ref h.model/fila-vazia)
                  :laboratorio3 (ref h.model/fila-vazia)}]

    (dotimes [pessoa 10]
     (async-chega-em hospital pessoa))
    (Thread/sleep 8000)
    (pprint hospital)
    )
  )

(simula-um-dia-async)