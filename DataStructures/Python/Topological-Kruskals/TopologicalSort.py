def incoming_edge_count(edge_list, vertex):
    count = 0
    for (from_vertex, to_vertex) in edge_list:
        if to_vertex is vertex:
            count += 1
    return count


def topological_sort(graph):
    result_list = []

    # Make list of vertices with no incoming edges
    no_incoming = []
    for vertex in graph.adjacency_list.keys():
        if incoming_edge_count(graph.edge_weights.keys(), vertex) == 0:
            no_incoming.append(vertex)

    remaining_edges = set(graph.edge_weights.keys())  # starts with all edges
    while len(no_incoming) is not 0:
        # select next vertex
        curr_vertex = no_incoming.pop()
        result_list.append(curr_vertex)
        outgoing_edges = []

        # remove current vertex outgoing edges from remaining edges
        for to_vertex in graph.adjacency_list[curr_vertex]:
            outgoing_edge = (curr_vertex, to_vertex)
            if outgoing_edge in remaining_edges:
                outgoing_edges.append(outgoing_edge)
                remaining_edges.remove(outgoing_edge)

        # check if removing outgoing edges creates new vertices with no incoming edges
        for (from_vertex, to_vertex) in outgoing_edges:
            in_count = incoming_edge_count(remaining_edges, to_vertex)
            if in_count == 0:
                no_incoming.append(to_vertex)

    return result_list
