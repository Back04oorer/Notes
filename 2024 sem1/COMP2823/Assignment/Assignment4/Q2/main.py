class MinHeap:
    def __init__(self):
        self.heap = []

    def parent(self, i):
        return (i - 1) // 2

    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def insert(self, value):
        self.heap.append(value)
        self._sift_up(len(self.heap) - 1)

    def extract_min(self):
        if len(self.heap) == 0:
            return None
        if len(self.heap) == 1:
            return self.heap.pop()

        root = self.heap[0]
        self.heap[0] = self.heap.pop()  # Replace root with last element
        self._sift_down(0)  # Rebalance heap
        return root

    def _sift_up(self, i):
        while i > 0 and self.heap[self.parent(i)] > self.heap[i]:
            # Swap with parent if current is smaller
            self.heap[self.parent(i)], self.heap[i] = self.heap[i], self.heap[self.parent(i)]
            i = self.parent(i)

    def _sift_down(self, i):
        while True:
            left = self.left(i)
            right = self.right(i)
            smallest = i

            if left < len(self.heap) and self.heap[left] < self.heap[smallest]:
                smallest = left

            if right < len(self.heap) and self.heap[right] < self.heap[smallest]:
                smallest = right

            if smallest != i:
                # Swap if not the smallest
                self.heap[i], self.heap[smallest] = self.heap[smallest], self.heap[i]
                i = smallest
            else:
                break


# Reading input and building MST
n = int(input())  # Number of vertices
m = int(input())  # Number of edges

mst_weight = 0  # MST total weight

edges = []
for _ in range(m):
    u, v, w = input().split()
    edges.append((int(u), int(v), float(w)))

n_A = int(input())  # Number of edges in set A
A = []
for _ in range(n_A):
    u, v = map(int, input().split())
    A.append((u, v))

edges_with_zero_A = []
for edge in edges:
    if (edge[0], edge[1]) in A or (edge[1], edge[0]) in A:
        mst_weight += edge[2]
        edges_with_zero_A.append((edge[0], edge[1], 0))
    else:
        edges_with_zero_A.append(edge)

n_B = int(input())  # Number of elements in set B
B = []
for _ in range(n_B):
    B.append(int(input()))

B_set = set(B)

min_edges_B = {}
for b in B_set:
    min_edges_B[b] = (None, float('inf'))

for edge in edges_with_zero_A:
    u, v, weight = edge
    if u in B_set and v not in B_set:
        if weight < min_edges_B[u][1]:
            min_edges_B[u] = ((u, v), weight)
    elif v in B_set and u not in B_set:
        if weight < min_edges_B[v][1]:
            min_edges_B[v] = ((v, u), weight)

edges_with_infinite_B = []
for edge in edges_with_zero_A:
    u, v, weight = edge
    if (u in B_set and v in B_set):
        continue
    elif (u in B_set and min_edges_B[u][0] != (u, v)) or (v in B_set and min_edges_B[v][0] != (v, u)):
        edges_with_infinite_B.append((u, v, float('inf')))
    else:
        edges_with_infinite_B.append((u, v, weight))

min_heap = MinHeap()  # Create a MinHeap instance

start_vertex = 0  # Starting vertex

for edge in edges_with_infinite_B:
    if edge[0] == start_vertex or edge[1] == start_vertex:
        min_heap.insert((edge[2], edge[0], edge[1]))

connected_vertices = {start_vertex}

while len(connected_vertices) < n and min_heap.heap:
    weight, u, v = min_heap.extract_min()
    new_vertex = v if u in connected_vertices else u
    
    if new_vertex not in connected_vertices:
        connected_vertices.add(new_vertex)
        mst_weight += weight
        
        # Insert all edges connected to the new vertex
        for edge in edges_with_infinite_B:
            if new_vertex in edge:
                min_heap.insert((edge[2], edge[0], edge[1]))

print('{:.2f}'.format(mst_weight))  # Print the MST total weight with two decimal places

