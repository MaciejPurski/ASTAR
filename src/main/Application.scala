/**
 * @author MateuszDorobek
 * @version 0.1
 */

package main
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec
import scala.io.Codec.decoder2codec
import java.nio.file.Files
import java.nio.file.Paths

//TODO Dokumentacja JavaDoc
/**
 * @object main object that contain main function
 *
 */
object obj {
  /**
   * main function, that creates graph structure out of file
   *
   * @param args array of arguments given to the program, when runnned from console
   *
   */
  def main(args: Array[String]) {
    
    print("Set input file path: ")
    val filePath = scala.io.StdIn.readLine()
    if(Files.notExists(Paths.get(filePath))){
      System.err.println("File path not exist")     
    	return
    }
    print("Begin node label: ")
    val beginNode = scala.io.StdIn.readLine()
   
    print("End node label: ")
    val endNode = scala.io.StdIn.readLine()
   
    println("A* algoritm searching shortest path between " + beginNode + " and " + endNode + ".\n");

    var graph = new Graph[String](Map())
    readInput(filePath)

    /**
     * Reads input file with UTF-8 encoding
     * 
     * then loads vertices, edges and heuristics into graph structure
     * in case of exception prints stack trace
     */
    def readInput(filename: String) {
      val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)
      val bufferedSource = Source.fromFile(filename)(decoder)
      var readMode = 0; //1 Vertices, 2 Edges, 3 Heuristics
      var lineCtr = 1;
      try {
        for (line <- bufferedSource.getLines) {
          if (line.equals("Vertices")) readMode = 1;
          else if (line.equals("Edges")) readMode = 2;
          else if (line.equals("Heuristics")) readMode = 3;
          else {
            readMode match {
              case 1 => graph = graph.addVertex(line)
              case 2 => graph = graph.addEdge(line.split("\\s+")(0), line.split("\\s+")(1), line.split("\\s+")(2).toInt)
              case 3 => graph = graph.addHeur(line.split("\\s+")(0), line.split("\\s+")(1), line.split("\\s+")(2).toInt)
            }
          }
          lineCtr += 1
        }
      } catch {
        case iae: IllegalArgumentException => println("error at line " + lineCtr + ": \"" + iae.getMessage)
      }
      bufferedSource.close
    }
    
      
    val path = graph.shortestPath(beginNode, endNode)
    println("Length: " + path.length)
    for (vertex <- path.visited)
      println(vertex)
  }
}
