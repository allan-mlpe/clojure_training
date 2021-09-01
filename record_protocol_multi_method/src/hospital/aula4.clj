(ns hospital.aula4
  (:use clojure.pprint))

(defrecord PacienteParticular [id, nome, situacao, nascimento])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, situacao, plano])

; função com várias implementações
;; No caso abaixo, quando chamarmos a função
;; `deve-assinar-pre-autorizacao-multi?` com qualquer parâmetro
;; ela irá retornar a classe, invocando a função `class`.
;;
;; Então, haverá a tentativa de chamar a função com o tipo
;; retornado. Caso não haja nenhum `defmethod` associado ao
;; tipo retornado, então uma execeção será lançada. Caso contrário
;; a função é chamada para o tipo em questão.
(defmulti deve-assinar-pre-autorizacao-multi? class)        ; `class` é a função que define qual estratégia será chamada

; quando chamarmos a função a seguir com uma instância
; de `PacienteParticular` é ela que será executada
(defmethod deve-assinar-pre-autorizacao-multi? PacienteParticular [paciente]
  (println "invocando paciente particular")
  true)

; quando chamarmos a função a seguir com uma instância
; de `PacientePlanoDeSaude` é ela que será executada
(defmethod deve-assinar-pre-autorizacao-multi? PacientePlanoDeSaude [paciente]
  (println "invocando paciente plano")
  false)

(let [paciente-particular (->PacienteParticular 1 "Bob" :normal "11/11/11")
      paciente-plano      (->PacientePlanoDeSaude 2 "João" "10/11/12" :urgente [:raio-x :sangue])]

  (println "### Estendendo com defmulti e defmethod ###")
  (println (deve-assinar-pre-autorizacao-multi? paciente-particular))
  (println (deve-assinar-pre-autorizacao-multi? paciente-plano)))

; pedido { :paciente paciente :valor valor :procedimento procedimento }
(defn tipo-de-autorizador
  [pedido]
  (let [paciente  (get pedido :paciente)
        situacao  (get paciente :situacao)
        urgencia? (= :urgente situacao)]
    (if urgencia?
      :sempre-autorizado
      (class paciente))))

; passamos para o `defmulti` a função `tipo-de-autorizador`
; o retorno dela será o "tipo" no qual nós chamaremos a função
; `deve-assinar-pre-autorizacao-pedido?`
; Contudo, para isso, devemos ter um `defmethod`
; associado a este tipo de retorno.
(defmulti deve-assinar-pre-autorizacao-pedido? tipo-de-autorizador)

; quando `tipo-de-autorizador` retornar `:sempre-autorizado`
; é a função a seguir que será executada.
(defmethod deve-assinar-pre-autorizacao-pedido? :sempre-autorizado
  [pedido]
  false)

; quando `tipo-de-autorizador` retornar `PacienteParticular`
; é a função a seguir que será executada.
(defmethod deve-assinar-pre-autorizacao-pedido? PacienteParticular
  [pedido]
  (>= (get pedido :valor) 50))

; quando `tipo-de-autorizador` retornar `PacientePlanoDeSaude`
; é a função a seguir que será executada.
(defmethod deve-assinar-pre-autorizacao-pedido? PacientePlanoDeSaude
  [pedido]
  (let [procedimento (get pedido :procedimento)
        paciente (get pedido :paciente)
        plano (get paciente :plano)]
    (not (some #(= % procedimento) plano))))


(let [paciente-particular (->PacienteParticular 1 "Bob" :normal "11/11/11")
      paciente-particular-2 (->PacienteParticular 1 "Tita" :urgente "11/11/11")
      paciente-plano      (->PacientePlanoDeSaude 2 "João" "10/11/12" :urgente [:raio-x :sangue])
      paciente-plano-2      (->PacientePlanoDeSaude 2 "Joãozinho" "10/11/12" :normal [:raio-x :sangue])]

  (println "\n\n\n### Estendendo com defmulti e defmethod ###")
  (println "## Misturando valores e classes ##")
  (println (deve-assinar-pre-autorizacao-pedido? {:paciente paciente-particular
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue}))
  (println (deve-assinar-pre-autorizacao-pedido? {:paciente paciente-particular-2
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue}))
  (println (deve-assinar-pre-autorizacao-pedido? {:paciente paciente-plano
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue}))
  (println (deve-assinar-pre-autorizacao-pedido? {:paciente paciente-plano-2
                                                  :valor 1000
                                                  :procedimento :coleta-de-sangue})))