import scala.collection.immutable.Map
import scala.collection.immutable.List

case class Vertex[VertexID, DistanceType](edgesList: List[Edge[VertexID, DistanceType]], heuristicMap: Map[VertexID, DistanceType])
case class Edge[VertexID, DistanceType](to: VertexID, distance: DistanceType)

class Graph[VertexID, DistanceType](vertices: Map[VertexID, Vertex[VertexID, DistanceType]]) {
  def addVertex(vertexID: VertexID, lineCtr: Int) = {
    if (findVertex(vertexID))
      throw new IllegalArgumentException("addVertex() error at line " + lineCtr + ": \"" + vertexID + "\" that vertex already exist.")
    new Graph[VertexID, DistanceType](vertices + (vertexID -> new Vertex[VertexID, DistanceType](List(), Map())))
  }

  def addEdge(from: VertexID, to: VertexID, distance: DistanceType, lineCtr: Int) = {
    if (!findVertex(from))
      throw new IllegalArgumentException("addEdge() error at line " + lineCtr + ": \"" + from + "\" that vertex does not exist.")
    if (!findVertex(to))
      throw new IllegalArgumentException("addEdge() error at line " + lineCtr + ": \"" + to + "\" that vertex does not exist.")
    if (findEdgeOrHeuristic(from, to))
      throw new IllegalArgumentException("addEdge() error at line " + lineCtr + ": \"" + from + "\"-->\"" + to + "\" that edge does exist. You are overwriting it.")
    new Graph[VertexID, DistanceType](vertices + (from -> Vertex[VertexID, DistanceType]((vertices(from).edgesList :+ new Edge[VertexID, DistanceType](to, distance)), Map())))
  }

  def addHeur(from: VertexID, to: VertexID, heuristic: DistanceType, lineCtr: Int) = {
    if (!findVertex(from))
      throw new IllegalArgumentException("addHeur() error at line " + lineCtr + ": \"" + from + "\" that vertex does not exist.")
    if (!findVertex(to))
      throw new IllegalArgumentException("addHeur() error at line " + lineCtr + ": \"" + to + "\" that vertex does not exist.")
    if (findEdgeOrHeuristic(from, to))
      throw new IllegalArgumentException("addHeur() error at line " + lineCtr + ": \"" + from + "\"<-->\"" + to + "\" that edge does exist. You are overwriting it.")
    new Graph[VertexID, DistanceType](vertices + (from -> Vertex[VertexID, DistanceType]((vertices(from).edgesList), vertices(from).heuristicMap + (to -> heuristic))))
  }

  def findVertex(vertexID: VertexID): Boolean = {
    vertices.contains(vertexID)
  }
  def findEdgeOrHeuristic(from: VertexID, to: VertexID): Boolean = {
    for (v <- vertices)
      if (v._1.equals(from) && (
        v._2.edgesList.exists(edge => edge.to.equals(to)) ||
        v._2.heuristicMap.exists(heur => heur._1.equals(to))))
        return true
    return false
  }

  def showVertices = {
    for (v <- vertices) println(v._1)
  }
  def showEdges = {
    for (
      v <- vertices;
      e <- v._2.edgesList
    ) println(v._1 + " " + e.to + " " + e.distance)
  }
  def showHeuristics = {
    for (
      v <- vertices;
      h <- v._2.heuristicMap
    ) println(v._1 + " " + h._1 + " " + h._2)
  }
}