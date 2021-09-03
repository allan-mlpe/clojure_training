(ns schemas-clojure.aula5
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int?))
(def Plano [s/Keyword])
(def Paciente
  {:id                          PosInt
   :nome                        s/Str
   :plano                       Plano
   (s/optional-key :nascimento) s/Str})

; validação de mapas
(def Pacientes
  {PosInt Paciente})

(def Visitas
  {PosInt [s/Str]})

; como estamos validando os schemas, não precisamos mais do
; `if-let` e nem lançar nenhuma exceção, uma vez que pela
; validação de schemas nós temos a garantia que, se o código
; chegar nesse ponto, é porque temos um paciente válido
;(s/defn adiciona-paciente :- Pacientes
;  [pacientes :- Pacientes, paciente :- Paciente]
;  (if-let [id (:id paciente)]
;    (assoc pacientes id paciente)
;    (throw (ex-info "Paciente não possui id" {:paciente paciente}))))

; nova implementação de `adiciona-paciente`
(s/defn adiciona-paciente :- Pacientes
  [pacientes :- Pacientes, paciente :- Paciente]
  (let [id (:id paciente)]
    (assoc pacientes id paciente)))

(s/defn adiciona-visita :- Visitas
  [visitas :- Visitas, paciente :- PosInt, novas-visitas :- [s/Str]]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))

(s/defn imprime-relatorio-de-visitas-de-paciente
  [visitas :- Visitas, paciente :- PosInt]
  (println "Visitas do paciente" paciente ":" (get visitas paciente)))

(defn testa-tudo []
  (let [joao {:id 1 :nome "João" :plano []}
        bob  {:id 2 :nome "Bob" :plano [:coleta-de-sangue]}
        joaozinho {:id 3 :nome "Joãozinho" :plano [:clinico-geral]}

        pacientes (reduce adiciona-paciente {} [joao bob joaozinho])

        visitas {}
        visitas (adiciona-visita visitas 15 ["1/1/1"])
        visitas (adiciona-visita visitas 20 ["2/2/2" "5/5/5"])
        visitas (adiciona-visita visitas 15 ["3/3/3"])
        ]

    (pprint pacientes)
    (pprint visitas)

    (imprime-relatorio-de-visitas-de-paciente visitas 15)

    ))


(testa-tudo)