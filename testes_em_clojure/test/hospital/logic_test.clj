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