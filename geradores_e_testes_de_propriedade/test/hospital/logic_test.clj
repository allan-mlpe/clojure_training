(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]
            [hospital.model :as h.model]
            [clojure.test.check.generators :as gen]
            [schema.core :as s]))

(s/set-fn-validation! true)

(defn cria-fila [& itens]
  (if (not (nil? itens))
    (reduce conj h.model/fila-vazia itens)
    h.model/fila-vazia))

(deftest cabe-na-fila?-test
  (testing "Cabe na fila vazia"
    (is (cabe-na-fila? {:espera (cria-fila)} :espera)))

  (testing "Cabe na fila de tamanho até 4 inclusive"
    (doseq [fila (gen/sample (gen/vector gen/nat 0 4))]     ; irá gerar uma amostra de 10 vetores de tamanho variando de 0 a 4
      (is (cabe-na-fila? {:espera (cria-fila fila)} :espera))))

  (testing "Não cabe na fila cheia"
    (is (not (cabe-na-fila? {:espera (cria-fila 1 2 3 4 5)} :espera))))

  (testing "Não cabe na fila mais do que cheia"
    (is (not (cabe-na-fila? {:espera (cria-fila 1 2 3 4 5 6)} :espera))))

  (testing "Não cabe em fila inexistente"
    (is (not (cabe-na-fila? {:espera (cria-fila)} :raio-x)))))

(deftest chega-em-test
  (testing "Chega na fila com sucesso"
    (is (= {:espera (cria-fila 42)}
           (chega-em {:espera (cria-fila)} :espera 42)))

    (is (= {:espera (cria-fila 27 12 11 56 42)}
           (chega-em {:espera (cria-fila 27 12 11 56)} :espera 42)))

    (is (= {:espera (cria-fila 1 98 42)}
           (chega-em {:espera (cria-fila 1 98)} :espera 42))))

  (testing "Chega na fila cheia deve lançar exceção"
    (is (thrown? clojure.lang.ExceptionInfo
                 (chega-em {:espera (cria-fila 1 23 3 41 22)} :espera 42))))

  (testing "Chega na fila inexistente deve lançar exceção"
    (is (thrown? clojure.lang.ExceptionInfo
                 (chega-em {:espera (cria-fila 23)} :triagem 42))))
  )


(deftest transfere-test
  (testing "Transfere pessoas quando há vagas"
    (let [hospital {:espera (cria-fila 21)
                    :raio-x (cria-fila 22)}]
      (is (= {:espera (cria-fila)
              :raio-x (cria-fila 22 21)}
             (transfere hospital :espera :raio-x))))

    (let [hospital {:espera (cria-fila 21 2 3 45 12)
                    :raio-x (cria-fila)}]
      (is (= {:espera (cria-fila 2 3 45 12)
              :raio-x (cria-fila 21)}
             (transfere hospital, :espera, :raio-x))))

    (let [hospital {:espera (cria-fila 2245 2)
                    :raio-x (cria-fila 21 44 3 11)}]
      (is (= {:espera (cria-fila 2)
              :raio-x (cria-fila 21 44 3 11 2245)}
             (transfere hospital, :espera, :raio-x))))
    )

  (testing "Recusa pessoas quando não há vagas"
    (let [hospital {:espera (cria-fila 21)
                    :raio-x (cria-fila 22 23 45 11 10)}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (transfere hospital :espera :raio-x)))))

  ; abaixo estamos validando coisas que restringimos
  ; utiliznado pré-condições. Cabe a nós decidirmos se
  ; isso faz sentido ou não no nosso contexto.
  (testing "Recusa pessoas quando fila de origem não existe"
    (let [hospital {:espera (cria-fila 21)
                    :raio-x (cria-fila 22 23 45 11 10)}]
      (is (thrown? java.lang.AssertionError
                   (transfere hospital :espera-deitado :raio-x)))))

  (testing "Recusa pessoas quando fila de destino não existe"
    (let [hospital {:espera (cria-fila 21)
                    :raio-x (cria-fila 22 23 45 11 10)}]
      (is (thrown? java.lang.AssertionError
                   (transfere hospital :espera :oftalmo)))))


  )
