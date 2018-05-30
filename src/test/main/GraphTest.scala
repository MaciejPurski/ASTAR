package test.main

import main.Graph
import org.junit._
import org.junit.Assert._

class GraphTest {
  @Test def emptyOnCreate {
    val g = new Graph(Map())
    assertTrue(g.isEmpty)
  }
  
  @Test def notEmptyonAdd {
    val g = new Graph[String](Map()).addVertex("Warszawa")
    assertFalse(g.isEmpty)
  }
  
  @Test def simpleAStar {
     val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Krakow")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Krakow", "Kielce", 150)
                                    .addHeur("Warszawa", "Krakow", 350)
                                    
    val path = g.shortestPath("Warszawa", "Krakow")
    
    assertEquals(path.length, 400)
  }
  
  @Test def differentPathsTest {
         val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Krakow")
                                    .addVertex("Lublin")
                                    .addEdge("Warszawa", "Kielce", 250)
                                    .addEdge("Kielce", "Krakow", 150)
                                    .addEdge("Warszawa", "Lublin", 120)
                                    .addEdge("Lublin", "Krakow", 300)
                                    .addHeur("Warszawa", "Krakow", 350)
                                    
    val path = g.shortestPath("Warszawa", "Krakow")
    
    assertEquals(path.length, 400)
    assertEquals(path.visited, List("Krakow", "Kielce", "Warszawa"))
    
  }
  
  @Test def biggerPathTest {
         val g = new Graph[String](Map()).addVertex("Warszawa")
                                    .addVertex("Kielce")
                                    .addVertex("Krakow")
                                    .addVertex("Lublin")
                                    .addVertex("Olsztyn")
                                    .addVertex("Bialystok")
                                    .addVertex("Gdansk")
                                    .addVertex("Bydgosz")
                                    .addVertex("Lodz")
                                    .addVertex("Poznan")
                                    .addVertex("Szczecin")
                                    .addVertex("Zielona Gora")
                                    .addVertex("Wroclaw")
                                    .addVertex("Katowice")
                                    .addVertex("Bydgoszcz")
                                    .addEdge("Warszawa", "Olsztyn", 212)
                                    .addEdge("Warszawa", "Kielce", 178)
                                    .addEdge("Warszawa", "Lublin", 166)
                                    .addEdge("Warszawa", "Bialystok", 470)
                                    .addEdge("Warszawa", "Lodz", 137)
                                    .addEdge("Warszawa", "Bydgoszcz", 270)
                                    .addEdge("Warszawa", "Poznan", 319)
                                    .addEdge("Gdansk", "Olsztyn", 170)
                                    .addEdge("Gdansk", "Bydgoszcz", 165)
                                    .addEdge("Bydgoszcz", "Lodz", 227)
                                    .addEdge("Lodz", "Katowice", 193)
                                    .addEdge("Katowice", "Krakow", 80)
                                    .addEdge("Gdansk", "Szczecin", 362)
                                    .addEdge("Szczecin", "Zielona Gora", 217)
                                    .addEdge("Szczecin", "Poznan", 235)
                                    .addEdge("Poznan", "Zielona Gora", 152)
                                    .addEdge("Poznan", "Lodz", 203)
                                    .addEdge("Poznan", "Wroclaw", 186)
                                    .addEdge("Zielona Gora", "Wroclaw", 156)
                                    .addEdge("Lodz", "Wroclaw", 209)
                                    .addEdge("Wroclaw", "Katowice", 195)
                                    .addEdge("Kielce", "Krakow", 115)
                                    .addHeur("Warszawa", "Krakow", 282)
                                    .addHeur("Bialystok", "Krakow", 470)
                                    .addHeur("Lublin", "Krakow", 259)
                                    .addHeur("Olsztyn", "Krakow", 468)
                                    .addHeur("Gdansk", "Krakow", 546)
                                    .addHeur("Lodz", "Krakow", 217)
                                    .addHeur("Poznan", "Krakow", 378)
                                    .addHeur("Wroclaw", "Krakow", 254)
                                    .addHeur("Zielona Gora", "Krakow", 409)
                                    .addHeur("Szczecin", "Krakow", 600)
                                    .addHeur("Kielce", "Krakow", 115)
                                    .addHeur("Katowice", "Krakow", 80)
                                    
    val path = g.shortestPath("Gdansk", "Krakow")
    
    assertEquals(665, path.length)
    assertEquals(List("Krakow", "Katowice", "Lodz", "Bydgoszcz", "Gdansk"), path.visited)
    
  }
}