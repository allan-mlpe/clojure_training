(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

(deftest cabe-na-fila?-test
  (testing "Cabe na fila vazia"
    (is (cabe-na-fila? {:espera []} :espera)))

  (testing "Cabe na fila com poucas pessoas"
    (is (cabe-na-fila? {:espera [1]} :espera))
    (is (cabe-na-fila? {:espera [1 2 3]} :espera))
    (is (cabe-na-fila? {:espera [1 2 3 4]} :espera)))

  (testing "Não cabe na fila cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5]} :espera))))

  (testing "Não cabe na fila mais do que cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]} :espera))))

  (testing "Não cabe em fila inexistente"
    (is (not (cabe-na-fila? {:espera []} :raio-x)))))

(deftest chega-em-test
  (testing "Chega na fila com sucesso"
    (is (= {:espera [42]}
           (chega-em {:espera []} :espera 42)))

    (is (= {:espera [27 12 11 56 42]}
           (chega-em {:espera [27 12 11 56]} :espera 42)))

    (is (= {:espera [1 98 42]}
           (chega-em {:espera [1 98]} :espera 42))))

  (testing "Chega na fila cheia deve lançar exceção"
    (is (thrown? clojure.lang.ExceptionInfo
           (chega-em {:espera [1 23 3 41 22]} :espera 42))))

  (testing "Chega na fila inexistente deve lançar exceção"
    (is (thrown? clojure.lang.ExceptionInfo
                 (chega-em {:espera [23]} :triagem 42))))
  )
