(ns programacao-funcional.aula3)

; PREDICATE - funções que retornam true ou false
; também é padrão adicionarmos `?` ao fim de predicados
(defn deve-aplicar-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(println (deve-aplicar-desconto? 100))
(println (deve-aplicar-desconto? 1000))

(defn valor-descontado
  [valor-bruto]
  (if (deve-aplicar-desconto? valor-bruto)
    (let [taxa-desconto 10/100
          desconto      (* valor-bruto taxa-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))