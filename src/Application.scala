
object obj {
  def main(args: Array[String]) {
    val g = new Graph[String,Int](Map())
    //TODO sprawdzamy w funkcjach typu add, �eby sprawdzi�, czy istnieje ju� taki wierzcho�ek
    //TODO sprawdzamy nadpisanie
    //TODO W przypadku heurystyk sprawdzamy czy heurystyka odwrotna ju� istnieje to zg�aszamy b��d nawet je�eli ma dobr� warto��
    val ver = g.addVertex("Warszawa").addVertex("P�ock").addVertex("Starachowice")
    val edg = ver.addEdge("Warszawa", "P�ock", 10)
      .addEdge("Starachowice", "P�ock", 15)
    val heur = edg.addHeur("Warszawa", "Starachowice", 20)
    ver.showVertices
    edg.showEdges
    heur.showHeuristics
  }
}