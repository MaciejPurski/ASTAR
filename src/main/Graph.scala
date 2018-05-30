package main

import scala.collection.immutable.Map

/**
 * Vertex structure, where edges have structure of List, 
 * because we will use them in sequential form and heuristics 
 * have structure of Map, because we will need to have fast access to specific elements very often
 *
 * @param edgesList list of edges, that have begin in this vertex
 * @param heuristicMap map of all heuristics, that have begin in this vertex
 */
case class Vertex[VertexID](edgesList: List[Edge[VertexID]], heuristicMap: Map[VertexID, Int])

/**
  * Edge structure
  *
  * @param to ID of ending vertex
  * @param distance distance or weight of edge
  */
case class Edge[VertexID](to: VertexID, distance: Int)

/**
 * Graph structure
 *
 */
class Graph[VertexID](vertices: Map[VertexID, Vertex[VertexID]]) {

  /** Returns new graph that contains new vertex
   *
   * @param vertexID ID of new vertex, that will be added to graph structure
   */
  def addVertex(vertexID: VertexID) = {
    if (findVertex(vertexID))
      throw new IllegalArgumentException("addVertex() error: " + vertexID +
                                        "\" that vertex already exist.")
    
    new Graph[VertexID](vertices +
                        (vertexID -> new Vertex[VertexID](List(), Map())))
  }

 /** Returns new graph that contains new edge
   *
   * @param from ID of begin vertex of edge
   * @param to ID of ending vertex of edge
   * @param distance distance or weight of edge
   */
  def addEdge(from: VertexID, to: VertexID, distance: Int) = {
    if (!findVertex(from))
      throw new IllegalArgumentException("addEdge() error: " + from +
                                         "\" that vertex does not exist.")

    if (!findVertex(to))
      throw new IllegalArgumentException("addEdge() error: \"" + to +
                                         "\" that vertex does not exist.")

    if (findEdgeOrHeuristic(from, to))
      throw new IllegalArgumentException("addEdge() error: " +
                                         from + "\"-->\"" +
                                         to + "\" that edge does exist. You are overwriting it.")
    
    if (findEdgeOrHeuristic(to, from))
      throw new IllegalArgumentException("addEdge() error: " +
                                         from + "\"-->\"" +
                                         to + "\" that edge does exist. You are overwriting it.")

    new Graph[VertexID](vertices +
                       (from -> Vertex[VertexID]((vertices(from).edgesList :+
                        new Edge[VertexID](to, distance)), Map())) + 
                        (to -> Vertex[VertexID]((vertices(to).edgesList :+
                        new Edge[VertexID](from, distance)), Map())))
  }

  /**
   * Returns new graph that contains new heuristic
   *
   * @param from ID of begin vertex of heuristic
   * @param to ID of ending vertex of heuristic
   * @param heuristic value of heuristic
   */
  def addHeur(from: VertexID, to: VertexID, heuristic: Int) = {
    if (!findVertex(from))
      throw new IllegalArgumentException("addHeur() error: \"" + from +
                                         "\" that vertex does not exist.")

    if (!findVertex(to))
      throw new IllegalArgumentException("addHeur() error at line: \"" +
                                          to + "\" that vertex does not exist.")

    new Graph[VertexID](vertices +
                       (from -> Vertex[VertexID]((vertices(from).edgesList),
                        vertices(from).heuristicMap + (to -> heuristic))))
  }

  /**
   * If graph has vertex with passed by argument ID function returns true, false otherwise
   * 
   * @param vertexID ID of vertex, that we need to check if it exist in graph structure
   */
  def findVertex(vertexID: VertexID): Boolean = {
    vertices.contains(vertexID)
  }

 /** If graph has vertex with passed by argument ID function returns true, false otherwise
   * 
   * @param from ID of begin vertex, of edge that we need to check if it exist in graph structure
   * @param to  ID of ending vertex, of edge that we need to check if it exist in graph structure
   */
  def findEdgeOrHeuristic(from: VertexID, to: VertexID): Boolean = {
    for (v <- vertices)
      if (v._1.equals(from) && (
        v._2.edgesList.exists(edge => edge.to.equals(to)) ||
        v._2.heuristicMap.exists(heur => heur._1.equals(to)))) {
        return true
      }
    
    return false
  }

  /**
   * This function prints graph vertices on console screen
   */
  def showVertices = {
    for (v <- vertices) println(v._1)
  }

  /**
   * This function prints graph edges on console screen
   */
  def showEdges = {
    for (
      v <- vertices;
      e <- v._2.edgesList
    ) println(v._1 + " " + e.to + " " + e.distance)
  }
 
  /**
   * This function prints graph heuristics on console screen
   */
  def showHeuristics = {
    for (
      v <- vertices;
      h <- v._2.heuristicMap
    ) println(v._1 + " " + h._1 + " " + h._2)
  }
  
  def isEmpty: Boolean = {
    vertices.isEmpty
  }

  /**
   * Helper function for accessing vertex's heuristic value
   * 
   * @param from
   * @param to
   */
  def getVertexHeurstic(from: VertexID, to: VertexID) =  {
    vertices(from).heuristicMap.getOrElse(to, 0)
  }

  /**
   * Path represents a graph path as a list of visited vertices
   */
   case class Path(visited: List[VertexID], length: Int) {
     /**
      * Compute objective function as: costFunction + heursticFunction
      * 
      * @param to Destination vertex for a heuristic function
      */
      def evaluate(to: VertexID): Int = length + getVertexHeurstic(visited.head, to)

      /**
       *  Returns a list of possible new paths created from an existing one
       */
      def expandNeighbours = for (edge <- vertices(visited.head).edgesList if !visited.contains(edge.to))
                                 yield Path(edge.to +: visited, length + edge.distance) 
   }

 /**
   * Performs A* algorithm.
   *
   * @param from Source VertexID
   * @param to destination VertexID
   *
   * @return Path object, which consists of a list of visited nodes in reversed order
   * and a length of the path
   */
  def shortestPath(from: VertexID, to: VertexID): Path = {
    def aStar(paths: List[Path]): Path = {
      if (paths.head.visited.head == to)
        paths.head
      else
        /* expand the best path, remove current best path and sort the new list */
        aStar((paths.head.expandNeighbours ++ paths.drop(1))
               .sortWith(_.evaluate(to) < _.evaluate(to)))
     }

    aStar(List(Path(List(from), 0)))
  }
}