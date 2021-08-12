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


(println "================ FILTER EM MAPAS ================")


(defn pago?
  [item]
  (> (:valor item) 0))

(defn gratuito?
  [item]
  (not (pago? item)))


(println "Filtrando passando `vals` do mapa:" (filter pago? (vals pedido)))
(println "Filtrando com função anônima:" (filter (fn [[_ valor]] (gratuito? valor)) pedido))
(println "Filtrando com lambda:" (filter #(pago? (second %)) pedido)) ; `second` pega o segundo parâmetro do lambda


(println "===== COMPOSIÇÃO =====")
(comp not pago?)                                            ; é uma composição de funções: not e pago
(def pago-com-composicao? (comp not gratuito?))             ; definimos um símbolo como a composição de 2 funções

(println (pago-com-composicao? {:valor 40}))

(println "=========================================================")
(println "Exercício - Contar total de certificados")
(def clientes [
               {:nome         "Guilherme"
                :certificados ["Clojure" "Java" "Machine Learning"]}
               {:nome         "Paulo"
                :certificados ["Java" "Ciência da Computação"]}
               {:nome         "Daniela"
                :certificados ["Arquitetura" "Gastronomia"]}])


(defn total-de-certificados
  [clientes]
  (->> clientes
       (map :certificados)                                  ; obtém a lista de certificados
       (map count)                                          ; gera uma lista com os números de certificados de cada cliente (3, 2, 2)
       (reduce +)))                                         ; realiza a soma da quantidade de certificados de casa usuário

(println (total-de-certificados clientes))