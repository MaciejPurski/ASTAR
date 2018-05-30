# ASTAR
Scala functional implementation of A* algorithm.

Graph is implemented as a generic map, which maps a VertexID type to a Vertex structure, which
contains a list of edges with distances and heuristic function values.


The application reads an input txt file in a format shown in an example file in files/ directory.
It prints on the console the shortest found path and its length.

Running program firstly will ask to set input file path (e.g. ./files/cities.txt )
Then it will ask for begin and end node labels.
