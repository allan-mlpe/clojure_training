(ns schemas-clojure.aula3
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int?))

(def Paciente
  {:id PosInt
   :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- PosInt, nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Joca"))
;(pprint (novo-paciente -15 "Joca"))

(defn maior-ou-igual-a-zero? [x] (>= x 0))

(def ValorFinanceiro (s/constrained s/Num maior-ou-igual-a-zero?))

(def Pedido
  {:paciente     Paciente
   :valor        ValorFinanceiro
   :procedimento s/Keyword})

(s/defn novo-pedido :- Pedido
  [paciente     :- Paciente
   valor        :- ValorFinanceiro
   procedimento :- s/Keyword]
  {:paciente paciente, :valor valor, :procedimento procedimento})

(pprint (novo-pedido (novo-paciente 1 "Bob") 79.9 :raiox-x))
(pprint (novo-pedido (novo-paciente 1 "Joãozinho") 0 :raiox-x))

; podemos usar validação de schemas com vetores
(s/defn imprime-lista-numeros
  [lista :- [s/Num]]
  (println lista))

(imprime-lista-numeros [1, 2, 3, 4, 5])
(imprime-lista-numeros [0 -8])
(imprime-lista-numeros [])

; nil é considerada uma sequência vazia
(imprime-lista-numeros nil)

; a chamada abaixo irá falhar
;(imprime-lista-numeros [:a :b :c])

; podemos também abstrair o schema
(def ListaNumeros [s/Num])

(s/defn imprime-lista-abstraida
  [lista :- ListaNumeros]
  (println lista))

(imprime-lista-abstraida [1, 2, 3, 4, 5])
(imprime-lista-abstraida [0 -8])
(imprime-lista-abstraida [])
(imprime-lista-numeros nil)