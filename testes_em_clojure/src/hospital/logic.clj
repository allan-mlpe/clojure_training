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
    (update hospital departamento conj pessoa)))