(ns colecoes.db)

(def pedido1 {:usuario 15
              :itens {:mochila { :id :mochila, :quantidade 2, :preco-unitario 80}
                       :camiseta { :id :camiseta, :quantidade 3, :preco-unitario 40}
                       :tenis    { :id :tenis, :quantidade 1}}})

(def pedido2 {:usuario 1
              :itens {:mochila { :id :mochila, :quantidade 4, :preco-unitario 80}
                       :camiseta { :id :camiseta, :quantidade 3, :preco-unitario 40}}})

(def pedido3 {:usuario 15
              :itens {:mochila { :id :mochila, :quantidade 2, :preco-unitario 80}
                       :camiseta { :id :camiseta, :quantidade 3, :preco-unitario 40}
                       :tenis    { :id :tenis, :quantidade 1}}})

(def pedido4 {:usuario 20
              :itens {:camiseta { :id :camiseta, :quantidade 3, :preco-unitario 40}
                       :tenis    { :id :tenis, :quantidade 1}}})

(def pedido5 {:usuario 8
              :itens {:mochila { :id :mochila, :quantidade 1, :preco-unitario 80}}})

(def pedido6 {:usuario 8
              :itens {:mochila { :id :mochila, :quantidade 2, :preco-unitario 80}
                       :camiseta { :id :camiseta, :quantidade 3, :preco-unitario 40}
                       :tenis    { :id :tenis, :quantidade 1}}})

(defn todos-os-pedidos
  []
  [pedido1, pedido2, pedido3, pedido4, pedido5, pedido6])