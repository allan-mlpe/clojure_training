(ns schemas-clojure.aula4
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

(pprint (s/validate Paciente {:id 1, :nome "Joca", :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 1, :nome "Bob", :plano [:raio-x, :coleta-de-sangue]}))
(pprint (s/validate Paciente {:id 1, :nome "Pito", :plano [], :nascimento "20/12/20"}))

; validação de mapas
(def Pacientes
  {PosInt Paciente})

(let [paciente {:id 1, :nome "Joca", :plano [:raio-x]}
      paciente-2 {:id 1, :nome "Bob", :plano [:raio-x]}
      paciente-3 {:id 1, :nome "Tita"}]
  (println "\n# Validando mapas")
  (pprint (s/validate Pacientes {15 paciente}))
  (pprint (s/validate Pacientes {15 paciente 16 paciente-2}))
  (pprint (s/validate Pacientes {}))

  ;(pprint (s/validate Pacientes {-3 paciente-2})) ; falha, pois a chave não é um inteiro positivo
  ;(pprint (s/validate Pacientes {19 paciente-3})) ; falha, pois paciente-3 não é um paciente
  )

