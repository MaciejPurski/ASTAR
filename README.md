# ASTAR
Scala functional implementation of A* algorithm.

Graph is implemented as a generic map, which maps a VertexID type to a Vertex structure, which
contains a list of edges and heuristic function values.


The application reads an input file in a format shown in an example file in files/ directory.
It prints on the console the shortest found path and its length.
The command line format:

astar <input_file_path> <begin_node> <end_node>
