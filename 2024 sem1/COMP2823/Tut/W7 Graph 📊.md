
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

## BFS

