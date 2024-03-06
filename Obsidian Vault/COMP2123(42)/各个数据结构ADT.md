Index-Based Lists (List ADT)

Positional Lists

Stack ADT

Queue ADT

Tree ADT

Node

Binary Tree Operations

Binary Tree Node

AVL Tree

Map

List-Based (unsorted) Map

Hash Table

Hash Function

Priority Queue

Heap

Heap-based implementation of a priority queue

Heap Sort

Heap in array implementation

Set

MultiSet

Graph

Union Find ADT

# **Index-Based Lists (List ADT)**

![[Untitled.png]]

# **Positional Lists**

![[Screen_Shot_2022-06-10_at_8.38.00_pm.png]]

![[Screen_Shot_2022-06-10_at_8.38.29_pm.png]]

# **Stack ADT**

![[Untitled 1.png]]

# **Queue ADT**

![[Untitled 2.png]]

# Tree **ADT**

![[Untitled 3.png]]

# **Node**

![[Untitled 4.png]]

# Binary Tree Operations

![[Untitled 5.png]]

# Binary Tree Node

![[Untitled 6.png]]

# AVL Tree

![[Untitled 7.png]]

# Map

![[Untitled 8.png]]

![[Untitled 9.png]]

## **List-Based (unsorted) Map**

get()/put() → O(n)

![[Untitled 10.png]]

  
  
**Tree-Based (sorted) Map → AVL Tree**

get()/put() → O(logn)

## Hash Table

![[Untitled 11.png]]

## Hash Function

- x mod N is mathematical notation for remainder
- A hash function h is usually the composition of two functions

![[Untitled 12.png]]

---

对于collision不同处理得出的不同的复杂度

1. Separate Chaining
    
    ![[Untitled 13.png]]
    
2. **Open addressing using Linear Probing**
    
    ![[Untitled 14.png]]
    
      
    
    **==Time complexity==**
    
    ![[Untitled 15.png]]
    
3. **Cuckoo hashing  
      
    **

![[Untitled 16.png]]

# Priority Queue

![[Untitled 17.png]]

max version → remove_max()

![[Untitled 18.png]]

![[Untitled 19.png]]

# Heap

1. insert() → O(logn)
2. remove_min/remove_max → O(logn)

## **Heap-based implementation of a priority queue**

![[Untitled 20.png]]

## Heap Sort

![[Untitled 21.png]]

## Heap in array implementation

![[Untitled 22.png]]

![[Untitled 23.png]]

# Set

![[Untitled 24.png]]

## MultiSet

![[Untitled 25.png]]

# Graph

[[Graph]]

![[Untitled 26.png]]

1. DFS & BFS on adjacency list representation of a graph → O(m + n)
2. **==Fibonacci heap==** is a PQ that can carry out decrease key in O(1) amortized time. Using that instead we get O(m + n log n) time.

![[Untitled 27.png]]

# **Union Find ADT**

![[Untitled 28.png]]

![[Untitled 29.png]]