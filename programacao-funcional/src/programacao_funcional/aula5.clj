(ns programacao-funcional.aula5)

(println "================== MAPS ==================")

; declarando um mapa
(def estoque {:mochila  10
              :camiseta 5})

(println "Mapa:" estoque)

; obtendo chaves do mapa
(println "Chaves:" (keys estoque))

; obtendo valores do mapa
(println "Valores:" (vals estoque))

; contando itens dentro do mapa
(println "Mapa com" (count estoque) "elementos")

;; Operações com mapa

(println " => Adicionando nova chave" (assoc estoque :bermuda 10)) ; cria um novo mapa com a chave associada
(println " => Removendo chave existente" (dissoc estoque :camiseta)) ; remove uma chave do mapa


; update recebe o mapa/coleção, a chave/índice que queremos atualizar e uma função
(println " => Atualizando valor" (update estoque :camiseta inc)) ; incrementa o valor da chave `camiseta`

; usando uma função anônima que remove 3 unidades
(println " => Atualizando valor" (update estoque :camiseta #(- %1 3))) ; incrementa o valor da chave `camiseta`

(println "\n\n\n=============================================")
(println "Obtendo valores do mapa...")

(println "Mapa como função:" (estoque :mochila))
(println "Chave (keyword) como função:" (:camiseta estoque))
(println "Chave (keyword) com valor padrão:" (:kombi estoque {}))
(println "Usando get:" (get estoque :mochila))
(println "Usando get (com valor padrão):" (get estoque :computador 0))


(println "\n\n\n=============================================")
(println "Objetos complexos")

(def pedido {:mochila  {:quantidade 2 :valor 139.8}
             :camiseta {:quantidade 4 :valor 99.}})

; para atualizar o valor de keywords aninhadas podemos usar o `update-in`
(println (update-in pedido [:camiseta :quantidade] inc))   ; aplica a função inc na quantidade de camisetas

(println "==== Obtendo valores aninhados ====")
(println "Usando keywords como função:" (:quantidade (:mochila pedido)))                   ; obtém a quantidade de mochilas
(println "Usando THREAD FIRST:" (-> pedido
                                    :camiseta
                                    :valor))                ; obtém o valor da camiseta

