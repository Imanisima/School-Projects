parent = dict()
rank = dict()


def create_set(vertex):
    parent[vertex] = vertex
    rank[vertex] = 0


# find set of that particular vertex
def find(vertex):
    if parent[vertex] is not vertex:
        parent[vertex] = find(parent[vertex])
    return parent[vertex]


# join two sets by rank
def union(vertex_a, vertex_b):
    root1 = find(vertex_a)
    root2 = find(vertex_b)

    if root1 is not root2:
        if rank[root1] > rank[root2]:
            parent[root2] = root1
        else:
            parent[root1] = root2

        # if ranks equal, increment rank by one
        if rank[root1] is rank[root2]:
            rank[root2] += 1


def kruskal(graph):
    for vertex in graph['vertices']:
        create_set(vertex) # set is chosen because it is easier to remove and add edges O(1)
        mst = set()
        edges = list(graph['edges'])
        edges.sort()

    for edge in edges:
        weight, vertex_a, vertex_b = edge
        if find(vertex_a) is not find(vertex_b):
            union(vertex_a, vertex_b)
            mst.add(edge)
            print(edge)

    # Sort graph in ascending order
    return sorted(mst)
