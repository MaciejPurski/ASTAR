import org.scalatest.FunSuite
import main.Graph

class AStarTest extends FunSuite {
  test("Simple test") {
    val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Kraków")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Kielce", "Warszawa", 250)
                                    .addEdge("Kielce", "Kraków", 150)
                                    .addEdge("Kraków", "Kielce", 150)
                                    .addHeur("Warszawa", "Kraków", 350)
                                    .addHeur("Kraków", "Warszawa", 350)
                                    
    val path = g.shortestPath("Warszawa", "Kraków")
    
    assert(path.length == 400)
    
  }
  
}

