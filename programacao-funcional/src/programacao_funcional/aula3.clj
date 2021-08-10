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

; passando uma funçao como parâmetro para outra função
(defn maior-que-100?
  [valor]
  (> valor 100))


(defn aplica-desconto-recebendo-funcao-como-parametro
  "Aplica um desconto casp função recebida determine que desconto deve ser aplicado"
  [deve-aplicar? valor-bruto]
  (if (deve-aplicar? valor-bruto)
    (let [desconto 0.1]
      (- valor-bruto (* valor-bruto desconto)))
    valor-bruto))

(println "========== RECEBENDO FUNÇÃO COMO PARÂMETRO ==========")
(println (aplica-desconto-recebendo-funcao-como-parametro maior-que-100? 100))
(println (aplica-desconto-recebendo-funcao-como-parametro maior-que-100? 1000))