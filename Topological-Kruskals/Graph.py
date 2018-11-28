class Vertex:
    def __init__(self, label):
        self.label = label


class Graph:
    def __init__(self):
        self.adjacency_list = {}
        self.edge_weights = {}

    def add_vertex(self, new_vertex):
        self.adjacency_list[new_vertex] = []

    def add_directed_edge(self, from_vertex, to_vertex, weight = 1):
        self.edge_weights[(from_vertex, to_vertex)] = weight
        self.adjacency_list[from_vertex].append(to_vertex)

    def add_undirected_edge(self, source, destination, weight = 1):
        self.add_directed_edge(source, destination, weight)
        self.add_directed_edge(source, destination, weight)
