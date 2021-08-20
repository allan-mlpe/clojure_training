(ns colecoes.aula3
  (:require [colecoes.db :as l.db]))


(def todos-os-pedidos (l.db/todos-os-pedidos))

(println "\n# TODOS OS PEDIDOS:\n" todos-os-pedidos)

(defn agrupa-pedidos-por-usuario
  [pedidos]
  (group-by :usuario pedidos))

(println "\n# PEDIDOS POR USUÁRIO:\n" (agrupa-pedidos-por-usuario todos-os-pedidos))

(defn quantidade-por-usuario
  [[usuario pedidos]]
  {:id-usuario usuario, :numero-pedidos (count pedidos)})

(defn quantidade-pedidos-por-usuario
  [pedidos]
  (->> pedidos
       (agrupa-pedidos-por-usuario)
       (map quantidade-por-usuario)))

(println "\n# QUANTIDADE POR USUÁRIO:\n" (quantidade-pedidos-por-usuario todos-os-pedidos))


(defn total-do-item
  [[_ detalhes]]
  (* (get detalhes :quantidade 0) (get detalhes :preco-unitario 0)))

(defn preco-total-pedido
  [pedido]
  (->> pedido
       (map total-do-item)
       (reduce +)))

(defn preco-total-pedidos
  [pedidos]
  (->> pedidos
       (map :itens)
       (map preco-total-pedido)
       (reduce +)))

(defn quantidade-pedidos-e-gasto-por-usuario
  [[usuario pedidos]]
  {
   :id-usuario      usuario
   :numero-pedidos  (count pedidos)
   :total-gasto     (preco-total-pedidos pedidos)
   })

(defn gasto-total-por-usuario
  [pedidos]
  (->> pedidos
       (agrupa-pedidos-por-usuario)
       (map quantidade-pedidos-e-gasto-por-usuario)))

(println "\n# NÚMERO DE PEDIDOS E GASTO TOTAL POR USUÁRIO:\n" (gasto-total-por-usuario todos-os-pedidos))