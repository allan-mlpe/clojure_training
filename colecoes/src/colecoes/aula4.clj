(ns colecoes.aula4
  (:require [colecoes.db :as l.db]
            [colecoes.logic :as l.logic]))

(def todos-os-pedidos (l.db/todos-os-pedidos))

(def resumo-usuarios (l.logic/gasto-total-por-usuario todos-os-pedidos))

(println "Resumo por usuário:" resumo-usuarios)


; ordenando a lista por total gasto
(def resumo-ordenado (sort-by :total-gasto resumo-usuarios))
(println "Resumo ordenado:" resumo-ordenado)

; pegando a lista reversa (dos maior gasto para o menor)
(def resumo-maior-gasto-para-menor (reverse resumo-ordenado))
(println "Resumo ordenado:" (reverse resumo-maior-gasto-para-menor))

; pegando o enésimo elemento da lista
(println "Enésimo elemento:" (nth resumo-maior-gasto-para-menor 2))

; pegando o total de elementos
(println "Total de elementos:" (count resumo-maior-gasto-para-menor))

; pegando os `n` primeiros elementos
(println "N primeiros elementos:" (take 2 resumo-maior-gasto-para-menor))

(defn top-2-gastos
  [resumo]
  (take 2 resumo))

(println "TOP-2 clientes:" (top-2-gastos resumo-maior-gasto-para-menor))

; checando se há algum elemento que obedece a alguma condição
(println (some #(> 500 (:total-gasto %)) resumo-maior-gasto-para-menor))