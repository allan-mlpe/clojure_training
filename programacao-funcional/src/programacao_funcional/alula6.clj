(ns programacao-funcional.alula6)

(println "================ MAP E REDUCE EM MAPAS ================")

(def pedido {:mochila  {:quantidade 2 :valor 139.8}
             :camiseta {:quantidade 4 :valor 99.}})

(defn imprimindo-mapa-retornando-42
  [mapa]
  (println mapa)
  42)


(println (map imprimindo-mapa-retornando-42 pedido))


; nesta função, recebemos apenas a chave como parâmetro
; uma vez que ela é o primeiro elemento dentro do array
(defn imprimindo-mapa-retornando-42-chave
  [[chave]]
  (println chave)
  42)

(println (map imprimindo-mapa-retornando-42-chave pedido))

; nesta função, recebemos a chave e valor como parâmetro dentro do array
(defn imprimindo-mapa-retornando-42-chave-valor
  [[chave valor]]
  (println "Chave:" chave ", Valor:" valor)
  42)



(println (map imprimindo-mapa-retornando-42-chave-valor pedido))

(println "=========== Calculando valor total dos itens do pedido ===========")

(defn calcula-valor-produtos
  [[_ valor]]                                               ; quando não utilizamos um valor, neste caso a chave, podemos passar `_`
  (* (:quantidade valor) (:valor valor)))

(println (map calcula-valor-produtos pedido))

(println "\n\n=========== Calculando preço total do pedido ===========")
(defn preco-total-pedido
  [pedido]
  (->> pedido                                               ; THREAD LAST - passa o resultado da função anterior como último parâmetro da função seguinte
       (map calcula-valor-produtos)
       (reduce +)))

(println (preco-total-pedido pedido))


(println "\n\n=========== Calculando preço total do pedido V2 ===========")
(println "Evitando destruct no map para tornar o código mais legível")

(defn calcula-valor-produtos-v2
  [produto]
  ;(println "Produto --->" produto)
  (* (:quantidade produto) (:valor produto)))

(defn preco-total-pedido-v2
  [pedido]
  (->> pedido                                               ; THREAD LAST - passa o resultado da função anterior como último parâmetro da função seguinte
       vals                                                 ; passamos a função `vals` para pegar apenas os valores do mapa
       (map calcula-valor-produtos-v2)
       (reduce +)))


(println (preco-total-pedido-v2 pedido))