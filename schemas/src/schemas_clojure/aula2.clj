(ns schemas-clojure.aula2
  (:use clojure.pprint)
  (:require [schema.core :as s]))

; habilita a validação
(s/set-fn-validation! true)

; para schemas não precisamos
; colocar `:-` antes dos tipos
(def Paciente
  {:id   s/Num
   :nome s/Str})

(pprint (s/explain Paciente))
(pprint (s/validate Paciente {:id 15, :nome "Bob"}))

; a linha abaixo falha, pois estamos
; passando `name` em vez de `nome`
;(pprint (s/validate Paciente {:id 15, :name "Bob"}))

; a função abaixo retornaria um erro
; na validação de saída, dado que o
; schema `Paciente` não possui um campo
; `plano`
;(s/defn novo-paciente :- Paciente                           ; estamos declarando que a função retorna um `Paciente`
;  [id :- s/Num, nome :- s/Str]
;  {:id id, :nome nome, :plano: []})

(s/defn novo-paciente :- Paciente                           ; estamos declarando que a função retorna um `Paciente`
  [id :- s/Num, nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Joquinha"))