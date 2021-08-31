(ns hospital.aula1
  (:use [clojure pprint]))

; criando uma "classe" com `defrecord`
(defrecord Paciente [id nome nascimento])

(defn teste-paciente []
  (let [paciente1 (->Paciente 15 "João" "11/11/11")         ; "instanciando" da forma comum
        paciente2 (map->Paciente {:id 25 :nome "Bob" :nascimento "12/12/12"}) ; "instanciando" como um mapa
        paciente3 (Paciente. 25 "Joãozinho" "10/10/10")     ; "instanciando" com o construtor padrão de Java
        ;paciente4 (Paciente. "Joãozinho" "10/10/10")     ; não podemos omitir um campo caso usarmos o construtor padrão
        paciente5 (map->Paciente {:id 27 :nome "Tita" :nascimento "14/14/14" :rg "897987899"})     ; podemos passar "campos extras" se criarmos o paciente como um mapa
        ]

    (pprint paciente1)
    (pprint paciente2)

    ; checa se o paciente é um record
    (println "# É um record?" (record? paciente1))

    ; acessando um campo como um mapa
    (println "# Campo como mapa:" (:nome paciente3))
    (println "# Todos os valores como mapa:" (vals paciente5))

    ; acessando um campo como uma chamada de método Java
    (println "# Campo como método:" (.nome paciente2))

    ; imprimindo a classe do record
    (println "# Classe do record:" (class paciente1))

    ; adicionando um novo campo com `assoc` ainda retorna a classe `Paciente`
    (pprint (map->Paciente {:foo "bar"}))
    (println "# Classe com campos diferentes:" (class (assoc (map->Paciente {}) :foo "bar")))
    )
  )

(teste-paciente)
