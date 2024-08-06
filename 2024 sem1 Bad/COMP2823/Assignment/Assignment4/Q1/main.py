import heapq


n = int(input())
m = int(input())


edges = []
for _ in range(m):
    u, v, w = input().split()
    edges.append((int(u), int(v), float(w)))

# Read the existing edge set A
n_A = int(input())
A = []
for _ in range(n_A):
    u, v = map(int, input().split())
    A.append((u, v))


mst_weight = 0


edges_with_zero_A = []
for edge in edges:
    if (edge[0], edge[1]) in A or (edge[1], edge[0]) in A:
        # If the edge is in A, set its weight to zero
        mst_weight += edge[2]
        edges_with_zero_A.append((edge[0], edge[1], 0))
    else:
        # Otherwise, keep the original weight
        edges_with_zero_A.append(edge)

# Create a min-heap for Prim's algorithm
min_heap = []

start_vertex = 0

for edge in edges_with_zero_A:
    if edge[0] == start_vertex or edge[1] == start_vertex:
        heapq.heappush(min_heap, (edge[2], edge[0], edge[1]))

# Set of connected vertices
connected_vertices = {start_vertex}

# Build the MST until all vertices are connected
while min_heap and len(connected_vertices) < n:

    weight, u, v = heapq.heappop(min_heap)
    new_vertex = v if u in connected_vertices else u
    
    if new_vertex not in connected_vertices:

        connected_vertices.add(new_vertex)
        mst_weight += weight
        
        # Add edges connected to the new vertex to the min-heap
        for edge in edges_with_zero_A:
            if new_vertex in edge:
                heapq.heappush(min_heap, (edge[2], edge[0], edge[1]))


print('{:.2f}'.format(mst_weight))



