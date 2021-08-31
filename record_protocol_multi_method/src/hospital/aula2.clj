(ns hospital.aula2
  (:use [clojure pprint]))

(defrecord PacienteParticular [id, nome, nascimento])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, plano])

; Protocol é uma espécie de interface, se quisermos um paralelo com Java
(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

; fazendo o record "implementar" o protocol
(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [_ _ valor]
   (>= valor 50)))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao?
    [paciente procedimento _]
    (let [plano (:plano paciente)]
      (not (some #(= % procedimento) plano)))))             ; só assina a autorização caso procedimento não esteja no plano

; também podemos fazer a extensão diretamente
(defrecord PacienteEmpresarial [id name nascimento coparticipacao]
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente _ _]
   (let [tem-coparticipacao (:coparticipacao paciente)]
     (true? tem-coparticipacao))))

(defn teste-protocol []
  (let [paciente-particular (->PacienteParticular 1 "Bob" "11/11/11")
        paciente-plano      (->PacientePlanoDeSaude 2 "João" "10/11/12" [:raio-x :sangue])
        paciente-empresarial (->PacienteEmpresarial 3 "Joãozinho" "12/11/10" false)]

    (println (deve-assinar-pre-autorizacao? paciente-particular :sangue 30))
    (println (deve-assinar-pre-autorizacao? paciente-particular :ultrassom 500))
    (println (deve-assinar-pre-autorizacao? paciente-plano :ultrassom 500))
    (println (deve-assinar-pre-autorizacao? paciente-plano :raio-x 700))
    (println (deve-assinar-pre-autorizacao? paciente-empresarial :raio-x 700))

    ))

(teste-protocol)