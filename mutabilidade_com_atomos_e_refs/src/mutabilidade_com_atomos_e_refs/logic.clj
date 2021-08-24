(ns mutabilidade-com-atomos-e-refs.logic)

(defn chega-em
  [hospital departamento pessoa]
  (update hospital departamento conj pessoa))               ; vamos aplicar a função `conj` passando a `pessoa` para atualizar a chave `departamento` no mapa `hospital`

(defn atende
  [hospital departamento]
  (update hospital departamento pop))                       ; aplicamos o `pop` no `departamento` recebido e devolvemos o `hospital` atualizado