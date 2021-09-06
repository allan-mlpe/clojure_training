(ns hospital.core
  (:require [clojure.test.check.generators :as gen]))

; exemplo de geração de dados com o a lib
; a função `gen/sample` gera, por padrão, 10 amostras
(println (gen/sample gen/boolean))                          ; gera amostra com 10 valores booleanos

; podemos também limitar o número de amostras geradas
; passando um parâmetro a mais para a função `gen/sample`
(println (gen/sample gen/large-integer 3))                  ; gera amostra com 3 inteiros apenas

; gerando sequência de strings
(println (gen/sample gen/string-alphanumeric 100))          ; gera amostra com 100 strings alfa-numéricas

; gerando amostras de vetores
; devemos usar a função `gen/vector` para gerar vetores
(println (gen/sample (gen/vector gen/nat)))                 ; gera amostra de 10 vetores de números naturais, de tamanhos variados
(println (gen/sample (gen/vector gen/nat 6)))               ; gera amostra de 10 vetores de tamanho fixo igual a 6
(println (gen/sample (gen/vector gen/nat 1 5)))             ; gera amostra de 10 vetores de tamanho variando de 1 a 5
(println (gen/sample (gen/large-integer* {:min 1})))        ; gera amostra de números aleatórios, onde o menor número gerado será 1