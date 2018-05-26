package test.main

import main.Graph
import org.junit._
import org.junit.Assert._

class GraphTest {
  @Test def emptyOnCreate {
    val g = new Graph(Map())
    assertTrue(g.isEmpty)
  }
  
  @Test def simpleAStar {
     val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Krakow")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Kielce", "Warszawa", 250)
                                    .addEdge("Kielce", "Krakow", 150)
                                    .addEdge("Krakow", "Kielce", 150)
                                    .addHeur("Warszawa", "Krakow", 350)
                                    .addHeur("Krakow", "Warszawa", 350)
                                    
    val path = g.shortestPath("Warszawa", "Krakow")
    
    assertEquals(path.length, 400)
  }
  
  @Test def differentPathsTest {
         val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Krakow")
                                    .addVertex("Lublin")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Kielce", "Warszawa", 250)
                                    .addEdge("Kielce", "Krakow", 150)
                                    .addEdge("Krakow", "Kielce", 150)
                                    .addEdge("Warszawa", "Lublin", 120)
                                    .addEdge("Lublin", "Krakow", 300)
                                    .addHeur("Warszawa", "Krakow", 350)
                                    .addHeur("Krakow", "Warszawa", 350)
                                    
    val path = g.shortestPath("Warszawa", "Krakow")
    
    assertEquals(path.length, 400)
    
  }
}