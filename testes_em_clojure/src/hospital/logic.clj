(ns hospital.logic
  (:require [schema.core :as s]
            [hospital.model :as h.model]))

(defn cabe-na-fila?
  [hospital departamento]
  (some-> hospital    ; `some` fará interrompe o fluxo e retorna `nil` caso o retorno de alguma função chamada em cadeia seja `nil`
      departamento
      count
      (< 5)))

(s/defn chega-em :- h.model/Hospital
  [hospital :- h.model/Hospital
   departamento :- s/Keyword
   pessoa :- h.model/PacienteID]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila cheia ou inexistente" {:pessoa pessoa :fila departamento}))))

(s/defn atende :- h.model/Hospital
  [hospital :- h.model/Hospital
   departamento :- s/Keyword]
  (update hospital departamento pop))

(s/defn proximo :- h.model/PacienteID
  [hospital :- h.model/Hospital
   departamento :- s/Keyword]
  (-> hospital
      departamento
      peek))

(s/defn transfere :- h.model/Hospital
  [hospital :- h.model/Hospital
   fila-origem :- s/Keyword
   fila-destino :- s/Keyword]
  (let [proximo-da-fila (proximo hospital fila-origem)]
    (-> hospital
        (atende fila-origem)
        (chega-em fila-destino proximo-da-fila))))