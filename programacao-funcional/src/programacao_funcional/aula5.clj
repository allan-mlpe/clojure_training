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
