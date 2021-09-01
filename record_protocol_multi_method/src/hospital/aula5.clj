(ns hospital.aula5
  (:use clojure.pprint))

(defn estrategia-autorizacao
  [pedido]
  (let [paciente  (get pedido :paciente)
        situacao  (get paciente :situacao)
        urgencia? (= :urgente situacao)]
    (cond (= :urgente situacao) :sempre-autorizado
          (contains? paciente :plano) :plano-de-saude
          :else :credito-minimo)))

(defmulti deve-assinar-pre-autorizacao? estrategia-autorizacao)

(defmethod deve-assinar-pre-autorizacao? :sempre-autorizado
  [pedido]
  (println "Chamando sempre autorizado...")
  (pprint pedido)
  false)

(defmethod deve-assinar-pre-autorizacao? :plano-de-saude
  [pedido]
  (println "Chamando paciente de plano...")
  (pprint pedido)
  (let [procedimento (get pedido :procedimento)
        paciente (get pedido :paciente)
        plano (get paciente :plano)]
    (not (some #(= % procedimento) plano))))

(defmethod deve-assinar-pre-autorizacao? :credito-minimo
  [pedido]
  (println "Chamando paciente de crédito mínimo...")
  (pprint pedido)
  (>= (:valor pedido) 50))

(let [paciente-particular {:id 1 :nome "Bob" :situacao :normal :nascimento "11/11/11"}
      paciente-particular-2 {:id 2 :nome "Tita" :situacao :urgente :nascimento "11/11/11"}
      paciente-plano      {:id 2 :nome "João" :nascimento "10/11/12" :situacao :urgente :plano [:raio-x :sangue]}
      paciente-plano-2      {:id 2 :nome "Joãozinho" :nascimento "10/11/12" :situacao :normal :plano [:raio-x :sangue]}]

  (println (deve-assinar-pre-autorizacao? {:paciente paciente-particular
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue}))
  (println (deve-assinar-pre-autorizacao? {:paciente paciente-particular-2
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue}))
  (println (deve-assinar-pre-autorizacao? {:paciente paciente-plano
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue}))
  (println (deve-assinar-pre-autorizacao? {:paciente paciente-plano-2
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue})))