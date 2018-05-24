import scala.collection.immutable.Map
import scala.collection.immutable.List
/**@class Vertex structure, where edges have structure of List, 
 * because we will use them in sequential form and heuristics 
 * have structure of Map, because we will need to have fast access to specific elements very often
 *
 * @param edgesList list of edges, that have begin in this vertex
 * @param heuristicMap map of all heuristics, that have begin in this vertex
 */
case class Vertex[VertexID, DistanceType](edgesList: List[Edge[VertexID, DistanceType]], heuristicMap: Map[VertexID, DistanceType])
/**@class Edge structure
 *
 * @param to ID of ending vertex
 * @param distance distance or weight of edge
 */
case class Edge[VertexID, DistanceType](to: VertexID, distance: DistanceType)
/**
 * @class Graph structure
 *
 */
class Graph[VertexID, DistanceType](vertices: Map[VertexID, Vertex[VertexID, DistanceType]]) {
  /**
   * @method this method returns new graph that contains new vertex
   *
   * @param vertexID ID of new vertex, that will be added to graph structure
   * @param lineCtr counter of line, which gave us that specific vertex, will be used in case of exception
   */
  def addVertex(vertexID: VertexID, lineCtr: Int) = {
    if (findVertex(vertexID))
      throw new IllegalArgumentException("addVertex() error at line " + lineCtr + ": \"" + vertexID + "\" that vertex already exist.")
    new Graph[VertexID, DistanceType](vertices + (vertexID -> new Vertex[VertexID, DistanceType](List(), Map())))
  }
  /**
   * @method this method returns new graph that contains new edge
   *
   * @param from ID of begin vertex of edge
   * @param to ID of ending vertex of edge
   * @param distance distance or weight of edge
   * @param lineCtr counter of line, which gave us that specific vertex, will be used in case of exception
   */
  def addEdge(from: VertexID, to: VertexID, distance: DistanceType, lineCtr: Int) = {
    if (!findVertex(from))
      throw new IllegalArgumentException("addEdge() error at line " + lineCtr + ": \"" + from + "\" that vertex does not exist.")
    if (!findVertex(to))
      throw new IllegalArgumentException("addEdge() error at line " + lineCtr + ": \"" + to + "\" that vertex does not exist.")
    if (findEdgeOrHeuristic(from, to))
      throw new IllegalArgumentException("addEdge() error at line " + lineCtr + ": \"" + from + "\"-->\"" + to + "\" that edge does exist. You are overwriting it.")
    new Graph[VertexID, DistanceType](vertices + (from -> Vertex[VertexID, DistanceType]((vertices(from).edgesList :+ new Edge[VertexID, DistanceType](to, distance)), Map())))
  }
  /**
   * @method this method returns new graph that contains new heuristic
   *
   * @param from ID of begin vertex of heuristic
   * @param to ID of ending vertex of heuristic
   * @param heuristic value of heuristic
   * @param lineCtr counter of line, which gave us that specific vertex, will be used in case of exception
   */
  def addHeur(from: VertexID, to: VertexID, heuristic: DistanceType, lineCtr: Int) = {
    if (!findVertex(from))
      throw new IllegalArgumentException("addHeur() error at line " + lineCtr + ": \"" + from + "\" that vertex does not exist.")
    if (!findVertex(to))
      throw new IllegalArgumentException("addHeur() error at line " + lineCtr + ": \"" + to + "\" that vertex does not exist.")
    if (findEdgeOrHeuristic(from, to))
      throw new IllegalArgumentException("addHeur() error at line " + lineCtr + ": \"" + from + "\"<-->\"" + to + "\" that edge does exist. You are overwriting it.")
    new Graph[VertexID, DistanceType](vertices + (from -> Vertex[VertexID, DistanceType]((vertices(from).edgesList), vertices(from).heuristicMap + (to -> heuristic))))
  }
  /**
   * @method if graph has vertex with passed by argument ID function returns true, false otherwise
   * 
   * @param vertexID ID of vertex, that we need to check if it exist in graph structure
   */
  def findVertex(vertexID: VertexID): Boolean = {
    vertices.contains(vertexID)
  }
  /** 
   * @method If graph has vertex with passed by argument ID function returns true, false otherwise
   * 
   * @param from ID of begin vertex, of edge that we need to check if it exist in graph structure
   * @param to  ID of ending vertex, of edge that we need to check if it exist in graph structure
   */
  def findEdgeOrHeuristic(from: VertexID, to: VertexID): Boolean = {
    for (v <- vertices)
      if (v._1.equals(from) && (
        v._2.edgesList.exists(edge => edge.to.equals(to)) ||
        v._2.heuristicMap.exists(heur => heur._1.equals(to))))
        return true
    return false
  }
  /**
   * @method this function prints graph vertices on console screen
   */
  def showVertices = {
    for (v <- vertices) println(v._1)
  }
  /**
   * @method this function prints graph edges on console screen
   */
  def showEdges = {
    for (
      v <- vertices;
      e <- v._2.edgesList
    ) println(v._1 + " " + e.to + " " + e.distance)
  }
  /**
   * @method this function prints graph heuristics on console screen
   */
  def showHeuristics = {
    for (
      v <- vertices;
      h <- v._2.heuristicMap
    ) println(v._1 + " " + h._1 + " " + h._2)
  }
}