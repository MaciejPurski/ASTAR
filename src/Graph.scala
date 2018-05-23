import scala.collection.immutable.Map

case class VertexID(id: Int)
case class Vertex(edgesList: List[Edge], heuristicMap: Map[VertexID, Int])
case class Edge(to: VertexID, distance: Int)

class Graph(vertices: Map[VertexID, Vertex]) {
  def addVertex(vertexID: VertexID) = new Graph(vertices + (vertexID -> new Vertex(List(), Map())))
  def showVertices = for (v <- vertices) println(v._1.id)
  def addEdge(from: VertexID, to: VertexID, distance: Int) =
    new Graph(vertices + (from -> Vertex((vertices(from).edgesList :+ new Edge(to, distance)), Map())))
  def showEdges = for (
    v <- vertices;
    e <- v._2.edgesList
  ) println(v._1.id + " " + e.to.id + " " + e.distance)
  def addHeur(from: VertexID, to: VertexID, heuristic: Int) =
    new Graph(vertices + (from -> Vertex((vertices(from).edgesList), vertices(from).heuristicMap + (to -> heuristic))))
  def showHeuristics = for (
    v <- vertices;
    h <- v._2.heuristicMap
  ) println(v._1.id + " " + h._1.id + " " + h._2)
}



//
//
//
//class Graph(someNodes:Set[N], val edges:Set[(N,N)]) {
//
//  // someNodes does not necessarily contains all nodes from the edges, nodes does
//  val nodes: Set[N] = someNodes ++ edges.map(_._1) ++ edges.map(_._2)
//
//  def addVortex(node:N) = new Graph(nodes + node, edges)
//
//  def addEdge(edge: (N, N)) = new Graph(nodes + edge._1 + edge._2, edges + edge)
//
//  // Redundant, but more efficient data structure for the outgoing edges
//  lazy val outgoingEdges: Map[N, Set[N]] = edges.groupBy(_._1).mapValues(s => s.map(_._2))
//
//  // Here come your algorithms
//}
//
