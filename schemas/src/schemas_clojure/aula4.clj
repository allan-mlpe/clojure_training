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



