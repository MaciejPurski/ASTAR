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
                                    .addVertex("Kraków")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Kielce", "Warszawa", 250)
                                    .addEdge("Kielce", "Kraków", 150)
                                    .addEdge("Kraków", "Kielce", 150)
                                    .addHeur("Warszawa", "Kraków", 350)
                                    .addHeur("Kraków", "Warszawa", 350)
                                    
    val path = g.shortestPath("Warszawa", "Kraków")
    
    assertEquals(path.length, 400)
  }
  
  @Test def differentPathsTest {
         val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Kraków")
                                    .addVertex("Lublin")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Kielce", "Warszawa", 250)
                                    .addEdge("Kielce", "Kraków", 150)
                                    .addEdge("Kraków", "Kielce", 150)
                                    .addEdge("Warszawa", "Lublin", 120)
                                    .addEdge("Lublin", "Kraków", 300)
                                    .addHeur("Warszawa", "Kraków", 350)
                                    .addHeur("Kraków", "Warszawa", 350)
                                    
    val path = g.shortestPath("Warszawa", "Kraków")
    
    assertEquals(path.length, 400)
    
  }
}