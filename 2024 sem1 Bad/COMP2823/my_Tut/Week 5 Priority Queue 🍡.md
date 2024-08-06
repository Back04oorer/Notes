
## Important tips

### Heap in array implementation

**Special Nodes**
- root is at index 0
- last node is at n-1

**For the node at index i**
- the left child is at index 2i+1
- the right child is at index 2i+2
- parent is at $\lfloor \frac{i-1}{2}\rfloor$ 


### Building a heap in one go

```PYTHON
def heapify(array):
	n = size(array)
	for i from n-1 to 0:
		down_heap(array,i)
```

The running time of the algorithm is $O(\sum^{n-1}_{i=0} h(i)) = O(n)$

For each node i in the tree construct a path of length $h(i)$ by starting at node i, going right once, and then going left util we reach a leaf.

### Time complexity analysis

![[Pasted image 20240326203549.png]]



### Sorting
#### Selection sort
```Python
def selection_sort(array): n = len(array) for i in range(0, n): 
	min_index = i 
	for j in range(i+1, n): 
		if array[j] < array[min_index]: 
			min_index = j 
		array[i], array[min_index] = array[min_index], array[i]
	
```

#### Insert sort
```Python
def insert_sort(array):
	n = len(array)
	for i in range(1,n):
		x = array[i] # the element that need to be inserted
		j = i
		while j > 0 and x < array[j-1]:
			array[j] = array[j-1]
			j = j - 1
		A[j] = x
	
``` 

#### Heap-sort
`remove_min` takes $O(log n)$ time ,n insertion can be done in $O(n)$ time. 
And with $n$  `remove_min` operations, the time complexity is $O(n log n)$ 

# Questions
### Q5 
Given an array A with n distinct integers, design an $O(n \log{k})$ time algorithm for finding the kth value in sorted order.
<mark style="background: #FF5582A6;">Hint: </mark>
1. create a min-heap with k elements (first k elements)  ---$O(k)$
2. iterates rest elements, if element is greater than the top of heap(min), then remove the top one,and insert this element ---$O((n-k)\log{k})$
3. The top value is the $k_{th}$ value 


### Q6
<mark style="background: #FFB8EBA6;">Hint</mark>: $\log{k}$  --> keep a min heap with k elements
K lists with `length m` , pick first element in each list...





