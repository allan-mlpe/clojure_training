(ns programacao_funcional.aula2)

; modo simples de definir a função para aplicar o desconto
(defn aplica-desconto
  [valor-bruto]
  (* valor-bruto 0.9))

(println (aplica-desconto 100))

; utilizando variáveis locais com o `let`
(defn valor-desconto-com-let
  "Retorna o valor do desconto"
  [valor-bruto]
  (let [desconto 0.1]
    (* valor-bruto desconto)))

(println (valor-desconto-com-let 1999))

; definindo mais de uma variável
(defn aplica-desconto-multiplas-variaveis
  "Aplica o valor de desconto e retorna o valor final do produto"
  [valor-bruto]
  (let [desconto        0.1
        valor-desconto  (* valor-bruto desconto)]
    (- valor-bruto valor-desconto)))

(println (aplica-desconto-multiplas-variaveis 500))

; utilizando condicionais para aplicar o desconto
(defn aplica-desconto-baseado-em-valor
  "Aplica um desconto de 10% caso o valor do produto seja maior de R$ 100,00"
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [desconto 0.1]
      (- valor-bruto (* valor-bruto desconto)))
    valor-bruto))

(println (aplica-desconto-baseado-em-valor 100))            ; não deve aplicar desconto
(println (aplica-desconto-baseado-em-valor 1000))           ; o desconto deve ser aplicado