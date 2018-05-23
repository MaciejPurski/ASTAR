import scala.collection.immutable.Map

case class Vertex[VertexID,DistanceType](edgesList: List[Edge[VertexID, DistanceType]], heuristicMap: Map[VertexID, DistanceType])
case class Edge[VertexID,DistanceType](to: VertexID, distance: DistanceType)

class Graph[VertexID,DistanceType](vertices: Map[VertexID, Vertex[VertexID, DistanceType]]) {
  def addVertex(vertexID: VertexID) = new Graph[VertexID, DistanceType](vertices + (vertexID -> new Vertex[VertexID, DistanceType](List(), Map())))
  def showVertices = for (v <- vertices) println(v._1)
  def addEdge(from: VertexID, to: VertexID, distance: DistanceType) =
    new Graph[VertexID, DistanceType](vertices + (from -> Vertex[VertexID, DistanceType]((vertices(from).edgesList :+ new Edge[VertexID, DistanceType](to, distance)), Map())))
  def showEdges = for (
    v <- vertices;
    e <- v._2.edgesList
  ) println(v._1 + " " + e.to + " " + e.distance)
  def addHeur(from: VertexID, to: VertexID, heuristic: DistanceType) =
    new Graph[VertexID, DistanceType](vertices + (from -> Vertex[VertexID, DistanceType]((vertices(from).edgesList), vertices(from).heuristicMap + (to -> heuristic))))
  def showHeuristics = for (
    v <- vertices;
    h <- v._2.heuristicMap
  ) println(v._1 + " " + h._1 + " " + h._2)
  
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
