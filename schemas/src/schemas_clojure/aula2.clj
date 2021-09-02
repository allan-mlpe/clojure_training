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

; podemos usar uma função para definir uma regra de validação
(defn estritamente-positivo? [x]
  (> x 0))

; passamos uma função para ser validada
; no schema como um predicate.
;
; assim, quando validarmos algum valor
; como `EstritamentePositivo` essa função
; será executada e caso seja false, um
; erro será retornado.
(def EstritamentePositivo (s/pred estritamente-positivo?))

(pprint (s/validate EstritamentePositivo 15))
;(pprint (s/validate EstritamentePositivo 0))
;(pprint (s/validate EstritamentePositivo -15))

; já existe uma função `pos?` de clojure que retorna se um número é positivo.
; já existe uma função `pos-int?` de clojure que retorna se um número é positivo E inteiro.
; abaixo, o `estritamente-positivo` é usado apenas para fins de demonstração
(def Paciente
  {:id   (s/constrained s/Int estritamente-positivo?)       ; `s/constrained` nos permite combinar schemas, assim o `id` tem que ser inteiro E estritamente positivo
   :nome s/Str})
