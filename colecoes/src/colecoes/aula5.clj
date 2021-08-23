(ns colecoes.aula5
  (:require [colecoes.db :as l.db]
            [colecoes.logic :as l.logic]))

(def resumo-pedidos (l.logic/gasto-total-por-usuario (l.db/todos-os-pedidos)))

; usando o `keep` em uma coleção
; `keep` funciona como um `map` + `filter`
; ele retorna o valor da função mapeada na lista. Caso o retorno seja nulo, então é retirado
(println (keep #(> (:total-gasto %) 300) resumo-pedidos))   ; retorna: true, true, false, true

(defn retorna-caso-maior-que-300
  [item-resumo]
  (if (> (:total-gasto item-resumo) 300)
    item-resumo))

(println (keep retorna-caso-maior-que-300 resumo-pedidos))  ; retorna: resumo do usuário `15` e `1`, que gastaram 560 e 440, respectivamente

