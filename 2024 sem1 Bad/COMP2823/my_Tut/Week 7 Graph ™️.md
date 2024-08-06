
## Terminologies
### Path
- Paths is a sequence of vertices such that every pair of consecutive vertices is connected by an edge.
- A  <mark style="background: #ABF7F7A6;"> simple path</mark> is a path where all vertices are distinct.A (simple) path from s to t is also called an s-t path.
### Cycle
- A cycle is defined by a path that starts and ends at the same vertex. (go through some vertices multiple times is ok )
- A <mark style="background: #BBFABBA6;">simple cycle</mark> is one where all vertices are distinct.
### Subgraph
- Let $G=(V, E)$ be a graph. We say $S=(U, F)$ is a <mark style="background: #BBFABBA6;">subgraph</mark> of G if $U \subseteq V$ and $F \subseteq E$.
- A subset $U \subseteq V$ induces a graph $G[U] = (U, E[U])$ where $E[U]$ are the edges in E with both endpoints in U
- A subset $F \subset E$ induces a graph $G[F] = (V[F], F)$ where $V[F]$ are the endpoints of edges in $F$.
### Connectivity
- A graph $G=(V, E)$ is <mark style="background: #ADCCFFA6;">connected</mark> if there is a path between every pair of vertices in V.
- A <mark style="background: #ADCCFFA6;">connected component</mark> of a graph G is a maximal connected subgraph of G.

### Trees an Forests
- An unrooted tree T is a graph such that : 
	- T is connected 
	- T has no cycles
- A forest is a graph without cycles. In other words, its connected components are trees.
<mark style="background: #BBFABBA6;">Fact</mark>: Every tree on n vertices has n-1 edges


## DFS

```PYTHON
def DFS(G): 
	# set things up for DFS 
	for u in G.vertices() do 
		visited[u] ← False 
		parent[u] ← None 
		
	# visit vertices 
	for u in G.vertices() do 
		if not visited[u] then 
			DFS_visit(u) 
	return parent

def DFS_visit(u): 
	visited[u] ← True 
	# visit neighbors of u 
	for v in G.incident(u) do 
		if not visited[v] then 
			parent[v] ← u 
			DFS_visit(v)
```

## Identify cutting-edge By DFS


- Compute a DFS tree of the input graph $G=(V, E)$
- For every $u$ in $V$, compute $level[u]$, its level in the DFS tree
- For every vertex $v$ compute the highest level that we can reach by taking DFS edges down the tree and then one back edge up. Call this $down\_and\_up[v]$
- Fact: A DFS edge $(u, v)$ where $u = parent[v]$ is a cut edge if and only if $down\_and\_up[v] > level[u]$

![[Pasted image 20240418033450.png]]


### How to calculate $down\_and\_up[v]$ ?

```PYTHON
function DFS_Bridges(G):
    initialize:
        for each vertex u in G.vertices():
            visited[u] ← False
            parent[u] ← None
            level[u] ← -1
            up[u] ← ∞
        time ← 0
        
    # 开始DFS遍历图
    for each vertex u in G.vertices():
        if not visited[u]:
            DFS_visit(u, 0)

    # DFS访问函数
    function DFS_visit(u, lvl):
        visited[u] ← True
        level[u] ← lvl
        up[u] ← lvl
        for each v in G.adjacent(u):
            if not visited[v]:
                parent[v] ← u
                DFS_visit(v, lvl + 1)
                # 检查子节点是否能连接到更早的祖先
                up[u] ← min(up[u], up[v])
                # 如果v的up值大于u的level，(u, v)是割边
                if up[v] > level[u]:
                    print("Bridge found:", u, v)
            elif v != parent[u]:
                # 更新u的up值，如果存在指向祖先的回边
                up[u] ← min(up[u], level[v])

    # 计算每个节点的down-and-up值
    for each vertex u in G.vertices():
        down-and-up[u] ← up[u]
        for each child v of u in DFS-tree:
            down-and-up[u] ← min(down-and-up[u], down-and-up[v])

```





## Find SCC(strongly connected component)
```PYTHON
# 输入：一个有向图 G = (V, E)
# 输出：G 的强连通分量集合

# Step 1: Run DFS on the original graph to get the finish order
function DFS_original(graph):
    visited = set()   # Set to keep track of visited nodes
    finish_order = [] # List to keep track of DFS finish order

    # DFS helper function
    def DFS_helper(node):
        visited.add(node) # Mark the node as visited
        for neighbor in graph.get_neighbors(node):
            if neighbor not in visited:
                DFS_helper(neighbor)
        finish_order.append(node) # Add to finish order when DFS completes for this node

	# Run DFS for each node that hasn't been visited
	for node in graph.get_nodes():
		if node not in visited:
			DFS_helper(node)

	return finish_order

# Step 2: Reverse the graph
function reverse_graph(graph):
    reversed_graph = new Graph() # Create a new graph with reversed edges
    for node in graph.get_nodes():
        for neighbor in graph.get_neighbors(node):
            reversed_graph.add_edge(neighbor, node) # Add reversed edge
    return reversed_graph

# Step 3: Run DFS on the reversed graph in the order of finish
function DFS_reversed(graph, finish_order):
    visited = set()      # Set to keep track of visited nodes
    components = []      # List to keep track of SCCs

    # DFS helper function to gather the nodes in SCC
    def DFS_helper(node, component):
        visited.add(node) # Mark the node as visited
        component.append(node) # Add node to the current component
        for neighbor in graph.get_neighbors(node):
            if neighbor not in visited:
                DFS_helper(neighbor, component)

    # Traverse the nodes in reverse order of finish
    for node in reversed(finish_order):
        if node not in visited:
            component = []
            DFS_helper(node, component) # Find the SCC
            components.append(component)

    return components

# Main function to find all SCCs
function find_SCCs(graph):
    finish_order = DFS_original(graph) # Step 1
    reversed_graph = reverse_graph(graph) # Step 2
    SCCs = DFS_reversed(reversed_graph, finish_order) # Step 3
    return SCCs

```
## BFS
pretty easy, update later
