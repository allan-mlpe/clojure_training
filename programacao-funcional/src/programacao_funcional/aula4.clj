(ns programacao-funcional.aula4)

(def precos [30 700 1000])

(println precos)

; obtendo índices da coleção
(println (precos 0))                                        ; forma básica, passando o vetor como uma função
; (println (precos 5))                                        ; retorna uma exceção
(println (get precos 0))                                    ; função get tb retorna valor na posição
(println (get precos 5))                                    ; caso o índice não exista na lista, retorna `nil`

(println "Retornando valor default quando índice não existe na coleção")
(println (get precos 5 5000))                               ; podemos passar um valor default como último parâmetro, caso índice não exista


; atualizando itens da coleção
(println "Função `inc` incrementa um número em 1 unidade")
(println (update precos 0 inc))                             ; retorna novo array com o valor na posição 0 incrementado em 1
(println "Função `dec` decrementa um número em 1 unidade")
(println (update precos 0 dec))                             ; retorna novo array com o valor na posição 0 decrementado em 1

; também podemos passar uma função customizada para o `update`
(defn soma-10
  [valor]
  (+ valor 10))

(println (update precos 1 soma-10))                         ; soma 10 ao número na posição 1 da lista