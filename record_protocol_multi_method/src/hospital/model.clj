(ns hospital.model)

(defprotocol Dateable
  (to-ms [this]))

(extend-type java.util.Date
  Dateable
  (to-ms [this] (.getTime this)))
