(ns mutabilidade-com-atomos-e-refs.aula4
  (:use [clojure pprint])
  (:require [mutabilidade-com-atomos-e-refs.model :as h.model]
            [mutabilidade-com-atomos-e-refs.logic :as h.logic]))

; quando uma função tem efeitos colaterais
; como a mudança de estado de um símbolo,
; devemos usar `!` para tornar isso explícito
(defn chega-em-teste!
  [hospital departamento pessoa]
  ; (swap! hospital update departamento conj pessoa)        ; fila sem limites
  (swap! hospital h.logic/chega-em departamento pessoa)     ; fila com limite
  )


(defn simula-um-dia-lazy []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["1" "2" "3" "4" "5"]
        chega-na-fila #(.start (Thread. (fn [] (chega-em-teste! hospital :espera %))))]

    ; como `map` ele só invocará a função quando precisarmos.
    ; nesse caso, ao imprimirmos o hospital, nenhuma mudança
    ; terá sido feita, já que não precisamos dos valores do map
    (map chega-na-fila pessoas)
    (pprint hospital)))


;(simula-um-dia-lazy)

(defn simula-um-dia-mapv []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["1" "2" "3" "4" "5"]
        starta-thread-entrada #(.start (Thread. (fn [] (chega-em-teste! hospital :espera %))))]

    ; o `mapv` irá forçar a criação de um vetor
    ; requerendo os valores do map.
    ; dessa forma nós forçamos que a função seja executada
    (mapv starta-thread-entrada pessoas)

    (pprint hospital)))

;(simula-um-dia-mapv)

(defn starta-thread-de-entrada
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-em-teste! hospital :espera pessoa)))))

; essa função age como um `partial` implementado na mão
(defn prepara
  [hospital]                                                ; recebemos primeiramente o hospital
  (fn [pessoa] (starta-thread-de-entrada hospital pessoa))) ; retornamos uma função que recebe o segundo parâmetro (`pessoa`) e faz a chamada para a função que starta a thread

(defn simula-um-dia-com-funcao-de-preparacao-manual []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["1" "2" "3" "4" "5"]]

    ; o `mapv` irá forçar a criação de um vetor
    ; requerendo os valores do map.
    ; dessa forma nós forçamos que a função seja executada
    (mapv (prepara hospital) pessoas)

    (pprint hospital)))

;(simula-um-dia-com-funcao-de-preparacao-manual)

(defn simula-um-dia-com-partial []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["1" "2" "3" "4" "5"]]

    ; o `mapv` irá forçar a criação de um vetor
    ; requerendo os valores do map.
    ; dessa forma nós forçamos que a função seja executada
    (mapv (partial starta-thread-de-entrada hospital) pessoas)

    (pprint hospital)))

;(simula-um-dia-com-partial)

(defn simula-um-dia-com-doseq []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["1" "2" "3" "4" "5"]]

    ; usamos `doseq` quando nos importamos em
    ; iterar por todos os elementos da coleção
    (doseq [pessoa pessoas]
      (starta-thread-de-entrada hospital pessoa))

    (pprint hospital)))

;(simula-um-dia-com-doseq)

(defn simula-um-dia-com-dotimes []
  (let [hospital (atom (h.model/novo-hospital))]

    ; usamos `dotimes` quando queremos executar
    ; uma função por um número definido de vezes
    (dotimes [pessoa 10]                                      ; executaremos a função 10 vezes
      (starta-thread-de-entrada hospital pessoa))

    (pprint hospital)))

(simula-um-dia-com-dotimes)