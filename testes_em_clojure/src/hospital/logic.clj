(ns hospital.logic)

(defn cabe-na-fila?
  [hospital departamento]
  (some-> hospital    ; `some` fará interrompe o fluxo e retorna `nil` caso o retorno de alguma função chamada em cadeia seja `nil`
      departamento
      count
      (< 5)))