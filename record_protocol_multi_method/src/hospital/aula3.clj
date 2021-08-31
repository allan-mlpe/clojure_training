(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]))

(defn carrega-paciente [id]
  (println "Carregando" id)
  (Thread/sleep 1000)
  {:id id, :carregado-em (h.logic/agora)})


(defn carrega-se-nao-existe
  [cache id funcao-carregadora]
  (if (contains? cache id)
    cache                                                   ; a função retorna o próprio cache
    (let [item-do-cache (funcao-carregadora id)]
      (assoc cache id item-do-cache))))                     ; a função retorna o próprio cache


(defprotocol Carregavel
  (carrega! [this id]))                                     ; essa função irá retornar o item do cache

(defrecord Cache
  [cache carregadora]
  Carregavel
  (carrega! [this id]
   (swap! cache carrega-se-nao-existe id carregadora)
   (get @cache id)))

(def pacientes (->Cache (atom {}), carrega-paciente))

(pprint pacientes)
(carrega! pacientes 15)
(carrega! pacientes 30)
(carrega! pacientes 15)                                     ; nesse caso, o paciente 15 já está no cache, não será carregado novamente
(pprint pacientes)



