
## Terminologies

### Shortest path
Given an edge weighted graph and two vertices u and v, we want to find a path of <mark style="background: #ABF7F7A6;">minimum total weight</mark> between u and v, where the weight of a path is the sum of the weights of its edges.

**Property**: A subpath of a shortest path is itself a shortest path.

**Property**: There is a tree of shortest paths from a start vertex to all the other vertices (shortest path tree).

## Dijkstra‘ algorithm
```PYTHON
def Dijkstra(Graph, source):

    D = {v: ∞ for v in Graph}  # 所有节点的距离估计初始化为无穷大
    parent = {v: None for v in Graph}  # 前驱节点
    D[source] = 0  # 起点的距离为0

    # 创建优先级队列，存储节点和其距离估计
    Q = PriorityQueue()  # 使用基于堆的优先级队列
    for v in Graph:
        Q.insert((v, D[v]))  # 插入所有节点及其距离估计

    # 迭代过程，直到队列为空
    while not Q.isEmpty():
        # 选择距离估计最小的节点
        u = Q.removeMin()  # 删除最小距离估计的节点

        # 检查u的相邻节点
        for z in Graph.neighbors(u):  # 获取与u相邻的节点
            # 计算通过u到达z的距离
            new_dist = D[u] + Graph.weight(u, z)  + Graph.value(z)# 边的权重
            # 如果这个新距离小于当前距离估计，进行边松弛
            if new_dist < D[z]:
                D[z] = new_dist  # 更新距离估计
                parent[z] = u  # 更新前驱节点
                Q.updatePriority((z, D[z]))  # 更新优先级队列中的优先级

    return D, parent  # 返回距离估计和前驱节点
```

### Complexity Analysis

Assuming the graph is connected (so $(m \geq n-1))$, the algorithm spends $O(m)$ time on everything except priority queue (PQ) operations.

### Priority Queue Operation Counts
- **Insert**: $n$ times
- **Decrease Key**: $m$ times
- **Remove Min**: $n$ times

### Fact
Using a heap for PQ, Dijkstra's algorithm runs in $O(m \log n)$ time.

## Minimum Spanning Tree (MST)
Given a connected graph $G = (V, E)$ with real-valued edge weights $c_e$ , an MST is a subset of the edges $T \subseteq E$ such that T is a spanning tree whose sum of edge weights is minimised.

####  properties
- **Simplifying assumption**. All edge costs $c_e$ are distinct.
- **Cut property**. Let S be any subset of nodes, and let e be the min cost edge with exactly one endpoint in S. Then the MST contains e.
	proof:
	1. **Simplifying assumption**: All edge costs are distinct, meaning there are no ties in edge costs.
	    
	2. **Cut property**: Given a subset of nodes S, identify the minimum-cost edge e with exactly one endpoint in S. The cut property asserts that the MST must include this edge.
	    
	3. **Proof by exchange argument**:
	    
	4. **Creating a cycle**: Let T* be the MST. Suppose e does not belong to T*. Adding e to T* creates a cycle C in T* because T* is a tree, and trees do not contain cycles.
	    
	5. **Intersection of cut and cycle**: Edge e is part of cycle C and also part of the cutset D corresponding to S. Since a cycle exists, there must be another edge f that is both in C and D.  <mark style="background: #BBFABBA6;">Cycle-Cut Intersection Property below</mark>
	    
	6. **Forming a new spanning tree**: Removing edge f from T* and adding edge e results in a new spanning tree T'.
	    
	7. **Checking costs**: Since e is the minimum-cost edge in S, and f is in the cycle, the cost of e, denoted as c_e, is less than the cost of f, denoted as c_f. Therefore, the cost of the new spanning tree T' is lower than that of T*.
	    
	8. **Conclusion**: This leads to a contradiction because T* is assumed to be the MST, implying that there cannot be a spanning tree with a lower cost. Thus, the assumption that e does not belong to T* is incorrect. By this contradiction, it's concluded that the MST must include e.

- **Cycle property**. Let C be any cycle, and let f be the max cost edge belonging to C. Then the MST does not contain f.
- ![[Pasted image 20240421172245.png]]
- ![[Pasted image 20240421172335.png]]


#### Cycle-Cut Intersection
Claim: A cycle and a cut set intersect in an even number of edges.


Proof:
![[Pasted image 20240421174705.png]]

###  Prim's Algorithm
```PYTHON
// 输入: 无向图 G = (V, E)，c 为每条边的权重
// 输出: 最小生成树的父节点数组 parent

function Prim(G, c):
    // 初始化顶点距离数组和父节点数组
    for each vertex v in V:
        d[v] ← ∞
        parent[v] ← ∅

    // 选择一个任意的起始顶点
    u ← arbitrary_vertex(V)
    d[u] ← 0
    
    // 初始化优先级队列 Q，其中包含所有顶点以及它们的初始距离
    Q ← new priority_queue((v, d[v]) for each v in V)
    
    // 创建一个空集合，用于跟踪已包含在最小生成树中的顶点
    S ← ∅

    // 当优先级队列不为空时，继续构建最小生成树
    while Q is not empty:
        // 从优先级队列中删除最小元素，并将其添加到集合 S
        u ← Q.delete_min()
        S ← S ∪ {u}

        // 遍历与顶点 u 相邻的所有边
        for each (u, v) in edges_incident_to(u):
            // 如果顶点 v 不在集合 S 中，并且该边的权重小于顶点 v 的当前距离
            if v ∉ S and c[u, v] < d[v]:
                // 更新顶点 v 的父节点和距离
                parent[v] ← u
                d[v] ← c[u, v]
                
                // 更新优先级队列中顶点 v 的优先级
                Q.decrease_priority(v, d[v])

    return parent

```