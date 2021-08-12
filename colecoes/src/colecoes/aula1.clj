(ns colecoes.aula1)


(def pessoas ["Joca" "Bob" "Joãozinho" "Lobão" "Tita" "Pitoquinha"])

; imprimindo todos os nomes da lista
(map println pessoas)

; criando um `map` manualmente
(println "Pegando o primeiro elemento do vetor com `first`:" (first pessoas))

(println "Pegando os elementos restantes do vetor com `rest`:" (rest pessoas))
(println "`rest` retorna uma sequência vazia quando o array é vazio:" (rest []))

(println "Pegando os elementos restantes do vetor com `next`:" (next pessoas))
(println "`next` retorna nil quando o array é vazio:" (next []))

(println "`seq`de um vetor é uma sequência com os itens do vetor" (seq [1 23 456]))
(println "\n\n`seq`de um vetor vazio é nil:" (seq []))


; utilizando first, rest e next para imlementar um map customizado.


(println "\n\n\n================== Implementando um `map` manualmente ==================")
(defn mapa-customizado
  "Esta função implementa uma espécie de `map` manualmente"
  [funcao lista]
  (let [primeiro-elemento (first lista)]
    (if (not (nil? primeiro-elemento))
      (do (funcao primeiro-elemento)                       ; utilizamos um bloco `do` para definir uma lista de instruções a serem executadas caso o `if` seja true
      (recur funcao (next lista))))))                      ; trocamos a chamada de `mapa-customizado` por `recur` para não termos problemas de estouro de pilha

(mapa-customizado println pessoas)
;(mapa-customizado println (range 100000))