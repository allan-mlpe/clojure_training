(ns colecoes.aula2)

; implementando um `count` manualmente
(println "-------------- Conta com `recur`")
(defn conta                                                 ; uma função com 2 variações (recebendo 1 ou 2 parâmetros)

  ([lista]                                                  ; definindo a mesma função com um parâmetro
    (conta 0 lista))

  ([total-ate-agora lista]                                  ; definindo a mesma função com 2 parâmetros
    (if (seq lista)
      (recur (inc total-ate-agora) (rest lista))
      total-ate-agora)))

(println (conta [1 2 3 4 5]))
(println (conta []))
(println (conta nil))


(println "-------------- Conta com `loop`")
(defn conta-com-loop
  [lista]
  (loop [total-ate-agora 0
         elementos-restantes lista]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes)) ; esse `recur` chamará o `loop` novamente, com aridade 2 (que são os valores definidos nos colchetes)
      total-ate-agora)))

(println (conta-com-loop [1 2 3 4 5]))
(println (conta-com-loop []))
(println (conta-com-loop nil))
