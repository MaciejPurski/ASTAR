
object obj {
  def main(args: Array[String]) {
    val g = new Graph(Map())
    //TODO sprawdzamy w funkcjach typu add, ¿eby sprawdziæ, czy istnieje ju¿ taki wierzcho³ek
    //TODO sprawdzamy nadpisanie
    //TODO W przypadku heurystyk sprawdzamy czy heurystyka odwrotna ju¿ istnieje to zg³aszamy b³¹d nawet je¿eli ma dobr¹ wartoœæ
    val ver = g.addVertex(VertexID(1)).addVertex(VertexID(2)).addVertex(VertexID(3))
    val edg = ver.addEdge(VertexID(1), VertexID(2), 10)
      .addEdge(VertexID(3), VertexID(2), 15)
    val heur = edg.addHeur(VertexID(1), VertexID(3), 20)
    ver.showVertices
    edg.showEdges
    heur.showHeuristics
  }
}