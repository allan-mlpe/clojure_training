(ns mutabilidade-com-atomos-e-refs.aula3
  (:use [clojure pprint])
  (:require [mutabilidade-com-atomos-e-refs.model :as h.model]))

; usando átomos para trabalhar com variáveis globais
(defn teste-atomao []
  (let [hospital-pe (atom {})]
    (pprint hospital-pe)

    ; para pegar o conteúdo do átomo, podemos usar deref ou @
    (pprint (deref hospital-pe))
    (pprint @hospital-pe)                                   ; `@` é um atalho para a função deref

    ; atualizar o valor do átomo diretamente NÃO funciona
    (pprint (assoc @hospital-pe :laboratorio1 h.model/fila-vazia))
    (pprint hospital-pe)                                    ; mesmo valor original

    ; para atualizar o valor de um átomo, devemos usar a função `swap!`
    (swap! hospital-pe assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-pe)                                   ; valor atualizado com um laboratório com uma fila vazia

    ; adicionando pessoas à fila
    (swap! hospital-pe update :laboratorio1 conj "111")
    (pprint @hospital-pe)))

(teste-atomao)