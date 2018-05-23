import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec

//TODO Dokumentacja JavaDoc
//TODO sprawdzamy nadpisanie
//TODO W przypadku heurystyk sprawdzamy czy heurystyka odwrotna ju¿ istnieje to zg³aszamy b³¹d nawet je¿eli ma dobr¹ wartoœæ

object obj {
  def main(args: Array[String]) {
    var g = new Graph[String, Int](Map())
    readInput
//    g.showVertices
//    g.showEdges
//    g.showHeuristics

    def readInput() {
      val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)
      val filename = "files/cities.txt";
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
              case 1 => g = g.addVertex(line, lineCtr)
              case 2 => g = g.addEdge(line.split("\\s+")(0), line.split("\\s+")(1), line.split("\\s+")(2).toInt, lineCtr)
              case 3 => g = g.addHeur(line.split("\\s+")(0), line.split("\\s+")(1), line.split("\\s+")(2).toInt, lineCtr)
            }
          }
          lineCtr += 1
        }
      } catch {
        case iae: IllegalArgumentException => iae.printStackTrace()
      }
      bufferedSource.close
    }

    //    val ver = g.addVertex("Warszawa").addVertex("P³ock").addVertex("Starachowice")
    //    val edg = ver.addEdge("Warszawa", "P³ock", 10).addEdge("Starachowice", "P³ock", 15)
    //    val heur = edg.addHeur("Warszawa", "Starachowice", 20)
    //    ver.showVertices
    //    edg.showEdges
    //    heur.showHeuristics
  }
}
