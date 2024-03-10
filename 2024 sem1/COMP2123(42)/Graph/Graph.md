Adjacent list

Graph Traversing

DFS

Time complexity

Properties

Applications

Cut edges

BFS

properties

Time complexity

Application

BFS vs DFS

Graph Algorithm

Dijkstra’s Algorithm

Time complexity

Correctness

Minimum Spanning Tree

Properties

Prim’s Algorithm

Time Complexity

Kruskal's Algorithm

Time complexity

Implementation

1. **An unrooted tree T is a graph such that**
    1. T is ==connected==
    2. T has ==no cycles==
    3. ==**Every tree on n vertices has n-1 edges**==
2. A spanning tree is a connected subgraph on the same vertex set
3. If a graph is connected → **m ≥ n-1 (m: number of edges; n: number of vertices)**
4. Important properties
    
    ![[/Untitled 48.png|Untitled 48.png]]
    
5. ADT
    
    ![[/Untitled 1 6.png|Untitled 1 6.png]]
    
6. 三种不同的structure的图
    1. ![[/Untitled 2 6.png|Untitled 2 6.png]]
        
    2. ![[/Untitled 3 4.png|Untitled 3 4.png]]
        
    3. ![[/Untitled 4 4.png|Untitled 4 4.png]]
        
7. Time complexity
    
    ![[Untitled 27.png]]
    

# Adjacent list

  

# Graph Traversing

1. DFS+BFS running time: **Given adjacency list representation of the graph with n vertices  
    and m edges both traversal run in  
    **==**O(n + m)**== **time**

# DFS

![[/Untitled 5 4.png|Untitled 5 4.png]]

![[/Untitled 6 4.png|Untitled 6 4.png]]

## Time complexity

![[/Untitled 7 4.png|Untitled 7 4.png]]

![[/Untitled 8 4.png|Untitled 8 4.png]]

## Properties

![[/Untitled 9 4.png|Untitled 9 4.png]]

## Applications

- Find a path between two given vertices, if any
- Find a cycle in the graph
- Test whether a graph is connected
- Compute connected components of a graph
- Compute spanning tree of a graph (if connected)
- testing bi-connectivity
- finding cut edges
- finding cut vertices

# Cut edges

![[/Untitled 10 4.png|Untitled 10 4.png]]

**The cut edge problem** ==**is to identify all cut edges**==  
  
**==three approaches:==**

![[/Untitled 11 4.png|Untitled 11 4.png]]

![[/Untitled 12 4.png|Untitled 12 4.png]]

![[/Untitled 13 4.png|Untitled 13 4.png]]

# BFS

![[/Untitled 14 4.png|Untitled 14 4.png]]

## properties

![[/Untitled 15 3.png|Untitled 15 3.png]]

## Time complexity

![[/Untitled 16 3.png|Untitled 16 3.png]]

## Application

- Find a shortest path between two given vertices
- Find a cycle in the graph
- Test whether a graph is connected
- Compute a spanning tree of a graph (if connected)
- **Testing if graph is bipartite**

# BFS vs DFS

![[/Untitled 17 3.png|Untitled 17 3.png]]

![[/Untitled 18 3.png|Untitled 18 3.png]]

# Graph Algorithm

1. Definition of the shortest path:
    
    ![[/Untitled 19 3.png|Untitled 19 3.png]]
    
2. Property of the shortest path :==A subpath of a shortest path is itself a shortest path==

# **Dijkstra’s Algorithm**

![[/Untitled 20 3.png|Untitled 20 3.png]]

![[/Untitled 21 3.png|Untitled 21 3.png]]

## Time complexity

1. **==Fibonacci heap==** is a PQ that can carry out decrease key in O(1) amortized time. Using that instead we get ==O(m + n log n) time==.
2. In ==**Dijkstra**==
    
    ![[/Untitled 22 3.png|Untitled 22 3.png]]
    

## **Correctness**

![[/Untitled 23 3.png|Untitled 23 3.png]]

![[/Untitled 24 3.png|Untitled 24 3.png]]

# Minimum Spanning Tree

![[/Untitled 25 3.png|Untitled 25 3.png]]

## Properties

1. **Simplifying assumption. All edge costs c**e ==**are distinct.**==
2. **Cut property**: Let S be any subset of nodes, and let ==e be the min cost edge with exactly one endpoint in S==. Then the MST contains e.
    
    ![[/Untitled 26 3.png|Untitled 26 3.png]]
    
3. **Cycle property. Let C be any cycle, and** ==**let f be the max cost edge belonging to C**==**. Then the MST does not contain f.**
    
    ![[/Untitled 27 3.png|Untitled 27 3.png]]
    
4. **Cutset. A cut is a subset of nodes S. The corresponding cutset D is the subset of edges** ==**with exactly one endpoint in S.**==
    
    ![[/Untitled 28 3.png|Untitled 28 3.png]]
    
5. **A cycle and a cutset intersect in an even number of edges.**
    
    ![[/Untitled 29 3.png|Untitled 29 3.png]]
    

## Prim’s Algorithm

![[/Untitled 30 3.png|Untitled 30 3.png]]

![[/Untitled 31 3.png|Untitled 31 3.png]]

### Time Complexity

![[/Untitled 32 3.png|Untitled 32 3.png]]

## **Kruskal's Algorithm**

![[/Untitled 33 3.png|Untitled 33 3.png]]

### **Time complexity**

![[/Untitled 34 3.png|Untitled 34 3.png]]

### Implementation

![[/Untitled 35 2.png|Untitled 35 2.png]]