
object obj {
  def main(args: Array[String]) {
    val g = new Graph[String,Int](Map())
    //TODO sprawdzamy w funkcjach typu add, ¿eby sprawdziæ, czy istnieje ju¿ taki wierzcho³ek
    //TODO sprawdzamy nadpisanie
    //TODO W przypadku heurystyk sprawdzamy czy heurystyka odwrotna ju¿ istnieje to zg³aszamy b³¹d nawet je¿eli ma dobr¹ wartoœæ
    val ver = g.addVertex("Warszawa").addVertex("P³ock").addVertex("Starachowice")
    val edg = ver.addEdge("Warszawa", "P³ock", 10)
      .addEdge("Starachowice", "P³ock", 15)
    val heur = edg.addHeur("Warszawa", "Starachowice", 20)
    ver.showVertices
    edg.showEdges
    heur.showHeuristics
  }
}