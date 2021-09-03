(ns hospital.logic)

(defn cabe-na-fila?
  [hospital departamento]
  (some-> hospital    ; `some` fará interrompe o fluxo e retorna `nil` caso o retorno de alguma função chamada em cadeia seja `nil`
      departamento
      count
      (< 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila cheia ou inexistente" {:pessoa pessoa :fila departamento}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))

(defn proximo
  [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  [hospital fila-origem fila-destino]
  (let [proximo-da-fila (proximo hospital fila-origem)]
    (-> hospital
        (atende fila-origem)
        (chega-em fila-destino proximo-da-fila))))