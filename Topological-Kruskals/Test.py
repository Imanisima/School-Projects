from Graph import *
from TopologicalSort import *
from KruskalAlgo import *

"""
Name: Imani M.
Lab: 6
Class: CS2302 1:30

Purpose: Implement Kruskal's algorithm and Topological sort and do at least one of the following:
    * Unit Tests
    * Creating a separate file where you call the implementations using hard-coded graphs
"""

print(" {     -TOPOLOGICAL SORT-    }"
      "\n|------------------------------|")
g = Graph()

# Nodes
vertex_A = Vertex('A')
vertex_B = Vertex('B')
vertex_C = Vertex('C')
vertex_D = Vertex('D')
vertex_E = Vertex('E')
vertex_F = Vertex('F')
vertex_G = Vertex('G')

# inserting into graph
g.add_vertex(vertex_A)
g.add_vertex(vertex_B)
g.add_vertex(vertex_C)
g.add_vertex(vertex_D)
g.add_vertex(vertex_E)
g.add_vertex(vertex_F)
g.add_vertex(vertex_G)

# Connecting
g.add_directed_edge(vertex_A, vertex_B)
g.add_directed_edge(vertex_A, vertex_C)
g.add_directed_edge(vertex_B, vertex_F)
g.add_directed_edge(vertex_C, vertex_D)
g.add_directed_edge(vertex_D, vertex_F)
g.add_directed_edge(vertex_E, vertex_F)
g.add_directed_edge(vertex_E, vertex_G)
g.add_directed_edge(vertex_F, vertex_G)

# Implementation
result_list = topological_sort(g)

# Display
first = True
for vertex in result_list:
    if first:
        first = False
    else:
        print(' -> ', end='')
    print(vertex.label, end='')
print("\n\n")

"""------------------------------------------------"""

print("  {   -KRUSKAL'S ALGORITHM-  }"
      "\n|------------------------------|")
g2 = {
    'vertices': ['A', 'B', 'C', 'D', 'E', 'F', 'G'],
    'edges': set([
        (7, 'A', 'B'),
        (5, 'A', 'C'),
        (10, 'B', 'F'),
        (9, 'B', 'D'),
        (8, 'C', 'B'),
        (3, 'D', 'G'),
        (2, 'E', 'F'),
        (6, 'G', 'A'),
        (5, 'F', 'E'),
    ])
}

print("Vertices: ", g2['vertices'])
print("Total Number of Edges In Graph: %d\n" % len(g2['edges']))
print("Minimal Spanning Tree: ")
kruskal(g2)
