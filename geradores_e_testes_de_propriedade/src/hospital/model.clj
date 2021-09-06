(ns hospital.model
  (:require [schema.core :as s]))

(def fila-vazia clojure.lang.PersistentQueue/EMPTY)

(s/def PacienteID s/Int)
(s/def Departamento (s/queue PacienteID))                   ; declarando uma fila de valores com a lib schema
(s/def Hospital { s/Keyword Departamento})