(ns mutabilidade-com-atomos-e-refs.logic)

(defn cabe-na-fila?
  [hospital departamento]
  (let [fila-departamento (get hospital departamento)
        tamanho-da-fila (count fila-departamento)]
    (< tamanho-da-fila 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
  (update hospital departamento conj pessoa)                ; vamos aplicar a função `conj` passando a `pessoa` para atualizar a chave `departamento` no mapa `hospital`
  (throw (ex-info "Fila cheia." {:pessoa-barrada pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))                       ; aplicamos o `pop` no `departamento` recebido e devolvemos o `hospital` atualizado