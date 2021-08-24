(ns mutabilidade-com-atomos-e-refs.colecoes
  (:import [clojure.lang PersistentQueue])
  (:use [clojure pprint]))                                  ; função de clojure para imprimir coisas no console de maneira mais amigável

(defn testa-vetor
  "Executa uma série de testes com um vetor"
  []
  (let [espera ["111" "222"]]                               ; definição de vetor: [...itens do vetor]
    (println espera)
    (println (conj espera "333"))                           ; adiciona "333" no fim do vetor
    (println (pop espera))))                                ; remove o último elemento do vetor

(println "# Teste com vetores")
(testa-vetor)

(defn testa-lista-ligada
  "Executa uma série de testes com uma lista ligada"
  []
  (let [espera '("111" "222")]                               ; definição de lista ligada: '(...itens da lista)
    (println espera)
    (println (conj espera "333"))                           ; adiciona "333" no início da lista
    (println (pop espera))))                                ; remove o primeiro elemento da lista

(println "\n# Teste com listas ligadas")
(testa-lista-ligada)

(defn testa-conjunto
  "Executa uma série de testes com um cojunto"
  []
  (let [espera #{"111" "222"}]                              ; definição de cojunto: #{...itens do conjunto}
    (println espera)
    (println (conj espera "333"))                           ; adiciona "333" no início da lista
    (println (conj espera "111"))                           ; se o elemento já existe no cojunto, se tentarmos adicionarmos novamente então a inserção é ignorada
    ;(println (pop espera))                                 ; `pop` não funciona em conjuntos, pois não há ordem específica dentro do conjunto
    ))

(println "\n# Teste com conjuntos")
(testa-conjunto)

(defn testa-fila
  "Executa uma série de testes com uma fila"
  []
  (let [espera (conj PersistentQueue/EMPTY "111" "222")]    ; definição de fila: clojure.lang PersistentQueue/EMPTY
    (pprint espera)
    (pprint (conj espera "333"))                           ; adiciona "333" no fim da fila
    (pprint (pop espera))                                  ; remove o primeiro elemento da fila
    (pprint (peek espera))))                               ; pega o primeiro elemento da fila

(println "\n# Teste com filas")
(testa-fila)