(ns schemas_clojure.aula1
  (:use clojure.pprint)
  (:require [schema.core :as s]))

; força a validação a ocorrer
; em tempo de execução
(s/set-fn-validation! true)

(s/defn teste-simpes [x :- Long]
  (println x))

(teste-simpes 15)

; se não invocarmos `set-fn-validation`
; as linhas abaixo serão executadas normalmente
;(teste-simpes [15 21])
;(teste-simpes "Alow")

(s/defn novo-paciente
  [id :- Long, nome :- s/Str]
  {:id id :nome nome})

(pprint (novo-paciente 15 "Joca"))

; se trocarmos os parâmetros da
; função a validação irá falhar
;(pprint (novo-paciente "Joca" 15))
