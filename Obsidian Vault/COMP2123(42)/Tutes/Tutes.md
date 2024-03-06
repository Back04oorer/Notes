3

4

5

6

7

8

11

# 3

**solution1**:构造一个只有右叶没有左叶的树 因为preorder → 根左右

- Insert the first element of the array as the root of the tree T. Iterate elements of the array from the second element.
    - In every iteration, insert the current element as the most right leaf node’s right child node.

![[/Untitled 46.png|Untitled 46.png]]

**solution2**

Create a node which value is the last element of the array and this node is the root of the Tree T. Iterate elements of the array from the second last elements to the first elements. In every iteration, create a new node with the current element’s value and insert the new node into the Tree T as the left most leaf node’s left child.

**solution3** ==*****==

Create a recursive function called calc_sum(root). In this function, if root does not have any child, return the root’s value. Else, return the ~~calc_sum(root.left) + root.value + calc_sum(root.right)~~.

If I want to calculate the subtree of u, I should call calc_sum(u) and it will return the subtree sum of node u.

==I use a recursive helper function to solve this problem.==

- ==When I call this function at some node in T, this function will recusively compute the sum of this node’s all children subtree size and add one to it and set this value as the node subtree size. Finally, return this node’s subtree size value to reused in other recursions.==

**solution4** ==*****==

Add a level attribute to every node and initiate it with 1.Use BFS to iterate nodes from the binary tree’s root. Everytime find a new node (which is some node’s child), set its level atrribute as its parent node’s level plus one. Create a new array named array and iterate the node again. In every iteration, if node’s level is equal to k, then add this node into the array. Return this result array.

==Use inorder traversal to traverse. I add a parameter “level” for recusive function used in inorder traversal to record current node’s level. In every recurrence, add one to the current level to set as the new level parameter in next recurrence. Also, only call visit function when level is equal to k.==

**solution5**

[ RETURN 下一个]

Test u has a child or not.

- If u has a left child, return u’s left child.
- Else if u only has a right child, return u’s right child.
- Else, this means u is an external node. Test u has parent and its position.
    - If u has no parent, return None.
    - If u has parent and parent left child is u. Then return parent’s right child.
    - If u has parent and parent right child is u. Then create a new variable named n which is equal to u.parent.
        - While n.parent is not None, if n is parent left child, then return the right child of n.parent. Else, n is equal to n.parent.
    - After the while loop, return None.

[better writing]

- I==f u.left is not None, then return u.left.==
- ==Else if u.left is None and u.right is not None, then return u.right.==
- ==Otherwise, this means u is an external node. Then let v to be the first ancestor of u which has right child and this child is not u. If this v exists, return v.right. Otherwise, u is the last node in this traversal and return None.==

  

**Solution6**

- If u has a right child, return u’s right child.
- If u does not has a right child and it has not a parent, return None.
- Else, if u does not has a right child and it has a parent node and it is the parent node’s left node, return the parent node.
- Else, if u does not has a right child and it has a parent node and it is the parent node’s right node, create a new variable named n which is equal to u’s parent node. While n.parent is not None, n is equal to n’s parent node. Return n.

==[better]==

- ==If u.right is not None,== ==then return the leftmost descendant of u.right.==
- ==Otherwise, move to the parent of u called v.==
    - ==If arrived at v is from its left subtree, then return v.==
    - ==Else, keep updating v (v = v.parent) until we find a v which satisfy that arrived at v is from its left subtree. then return v. If v is root, then there is no next node for u.==

**Solution7** ==*****==

- If u has no parent then return None.
- If u has parent and u is the left child and is a leaf.
    - If u’s parent has a right child, then return u’s parent’s right child.
    - Else, return its parent.

[ better ]

- ==If u is the root of this tree, then return None.==
- ==Else, call the parent of u as v.==
    - ==If u is the right child of v, then return v.==
    - ==If u is the left child of v and v has right child, then return v.right.==
    - ==If u is the left child of v and v has no right child, then return v.==

**Solution8**

- Create a recursive function called calc_bf(root).
    - If root is a None value, then return 0.
    - If root is an external node, then return root’s value.
    - Else, return the abusolute value of calc_bf(root.left) - calc_bf(root.right).

[ better ]

- ==I use a recursive helper function that takes node u as input and compute the balance factor of u and return the subtree’s height rooted at u.==
- ==Here is how I compute the balance factor of u:==
    - ==set left subtree height and right subtree height of u both -1.==
    - ==If the u.left is not None, then left is equal to the return value of the recursive function that takes node u.left as input.==
    - ==If the u.right is not None, then right is equal to the return value of the recursive function that takes node u.right as input.==
    - ==Then u.balance_factor is equal to the |left - right|.==
    - ==Return the value of 1 + max(left, right) as the subtree’s height rooted at u.==

**Solution9**

- Build an hashmap called visited and the keys are all nodes and value are all False(this hashmap size is the number of nodes). Build an queue based by array and enqueue the root of the tree.
- While the queue is not empty, keep dequeue the element. Set the value in visited which key is this element to True. And iterate the neighbours of this element and test every neighbourhood’s visited value. If the visited value is False, then enqueue it.

[ better ]

、、、

**Solution10** ==*****==

(找到最大depth的俩个branch然后向下探索)

(计算以每一个节点为根的树的高度 每一层都选择最大高度的两个向下深入)

Calculate subtrees height which were rooted at the different nodes. In every level of this tree, choose the biggest height to go deepper.

[ better ]

- Use modified pre_order (recursive function) to calculate every node’s depth in this tree.
    - Add another parameter named ‘level’ in the recursive function and the visit function is substituted with the assignment which assigns ‘level’ value to u.depth.
    - When recursively call the function on u’s children, where the level parameter is set to one plus u’s level.
    - To calculate all every node’s depth in this tree, just simply call this recursive function on the root of tree with level value being 0.
- Case 1: If one of the node is root, then we can just simply find the deepest node in this tree and return the path which it back to the root. The height of this tree is the length of this path.
- Case 2: In the second case, if we let _u_ be the lowest common ancestor of the leaves and let _v_ and _w_ be the two children of _u_ that are ancestors of these two leaves, then the length of the path is depth (v) + depth(w) - depth(u) * 2.

# 4

**Solution 1**

```Java
def trinode_re(x, y, z)
	A = x.left
	B = x.right
	C = y.right
	D = z.right
	if z is its parent's left child:
		z.parent.left = y
	else if z is its parent's right child:
		z.parent.right = y
	// 下面这行要记得写
	y.right = z
	y.parent = z.parent
	z.parent = y
	z.left = C
```

**Solution 2**

N(h) represents the maximum keys which the tree whose the height is h can contains. N(1) = 1, N(2) = 3 and for h > 1 N(h) = 1 + 2N(h - 1) where 2N(h - 1) represents the left and right subtree sizes and 1 represents the root.

Using inequality induction, we can prove that, N(h) = 2**h - 1.  
Base Case: N(1) = 2 * 1 - 1 = 2 and N(2) = 2 * 2 - 1 = 3 ⇒ Correct!  

Inductive Hypothesis: N(n) = 2**n - 1.

Inductive Case: N(n + 1) = 1 + 2N(n) = 1 + 2 * 2**n - 2 = 2 ** (n + 1) - 1 ⇒ correct

> [!important]  
> 多使用数学证明 inductive同样也是指 y(n) 是 y(n - 1)怎么怎么样得到的  

**Solution 3**

Base case: When n = 1, the external node is equal to 2. Therefore is correct:

Inductive hypothesis: The number of external nodes is _n_ + 1 which the tree nodes is n.

Inductive Case:

When n = n + 1, which means one of the external node in the tree size is n becomes to the internal node (leaf). One Ieaf has two external node and there is one external node disappears.

Therefore the number of external nodes of tree sized n + 1 is the number of external nodes of tree sized n minus one and plus two which is n + 2 which is n + 1 + 1. That is correct.

**Solution 4**

This algorithm is incorrect. The counter example can be

4

/ \

1 5

/

3

The key 3 is in the right subtree of root where key value is 4(To satisfys BST, this key should in the 4’s left subtree) which means this example should return False. However, when test the node 5, the node 3 is its left and is smaller than it, this function will return True. That is a contradiction. Therefore it is wrong.

**Solution 5**

According to the property of BST, the largest node of a BST is the right most child of BST.

To return the right most child in BST, recursively search on the BST’s root’s right subtree.

- If current node has right child, then recursively search on its right child.
- If current node’s right child is null, then return the current node’s key as the largest key.

  

Because the property of BST is u.left < u < u.right, therefore the right most child of BST must be the largest node of the BST.

Because every recursion only takes O(1) time and the recursion will run at most h time where h is the height of BST. Therefore the time complexity of this algorithm is O(h).

**Solution 6**

==<优先性反了！>==

- Firstly, find the largest key node in the BST which is the right most node.
- If the largest node has a parent, then return its parents as the second largest node.
- If the largest node has no parent:
    - If the largest node has a left child node, then return its left child node as the second largest node.
    - else, return null.

==【correct】应该是先测试有无左子再测试有无父母==

- Firstly, find the largest key node in the BST which is the right most node.
- If the largest key node has a left child, then return its left child node as the second largest node.
- Else, if this largest node has a parent, then return its parents as the second largest node.
- Otherwise, return null.

**Solution 7**

- augment the insertion and delete to rebalance the BST as AVL.
- If the n is an odd number, then return the root.
- Else, return the left child of the root.

==[ answer rewrite] 这个解法比较妙， 它不仅是找median，它也可以找到第k个元素。==

- if k ≤ 0, return null.
- Add one more attribute to every node: This attribute is “size” which means the size of the subtree which is rooted at the current node. I augment the insertion and the delete routines to maintain the “size” attribute.
    - In the insertion operation: When add one to the current node, the current node and its all ancestors “size” attribute is added one.
    - In the delete operation: When delete the current node, its all ancestors “size” attribute is minused one.
- By using this new attribute, I create a new operation named position(k, u) which could return the k-th node in this tree.
    - If u.left.size ≥ k, then k-th node is in the left sub-tree of the current node u. recursively call position(k, u.left) to search.
    - If u.left.size = k - 1, then k-th node is the current node, return it as the result.
    - If u.left.size < k - 1, then k-th node is in the right sub-tree of the current node u. recursively call position(k, u.right) to search.
    - If u is an external node, then there is no k-th node inside, return null.

**Solution 8**

- Use inorder traversal to test every node.
- If current node is in the range, then remove;
- If current node < k1, then recursive search on its right child
- else, recursively search on its left child.

[ correct answer ]

- Build two simple functions first.
    - Remove_greater(w, k): This function is used to remove the nodes which key is greater or equal to k from the BST rooted at w.
        - Firstly, find the node u with the k key value. If there is no such node, return None.
        - Secondly, v represents nodes which has right subtree on the path w to u. Delete all right subtree of v which must be greater than k.
        - Thirdly, n represents nodes on the path w to u. If u is the left descendants of n, delete all n. Because n has one external node, then deleting n only takes O(1).
    - Remove_smaller(w, k):This function is used to remove the nodes which key is smaller or equal to k from the BST rooted at w.
        - Firstly, find the node u with the k key value. If there is no such node, return None.
        - Secondly, v represents nodes which has left subtree on the path w to u. Delete all left subtree of v which must be greater than k.
        - Thirdly, n represents nodes on the path w to u. If u is the right descendants of n, delete all n. Because n has one external node, then deleting n only takes O(1).
- Remove_All(k1, k2): Use range search (introduced in the lecture) to find the first node x which x is inside [k1, k2]. Call remove_greater(x.left, k1) and remove_smaller(x.right, x2).

# 5

**Solution 3**

Constructing a min heap containing n inputs takes O(n) time and doing n times remove_min() takes n * O(logn) time. Time complexity is O(nlogn).

Constructing a min heap containing n/2 inputs takes O(n) time and doing n times remove_min() takes n/2 * O(log(n/2)) time. The lower bound proves that Ω(_n_ log _n)._

  

**Solution 5**

- Use a max heap to store the first k element in the array A. Iterate the element not in the max heap. In every iteration:
    - If the current element is smaller than the max element, remove the max element and insert the current element.
- After the loop, the max element of max heap is the k-th value in sorted order.
- Time complexity: insert and remove both takes O(logk) times which may run n - k times. Therefore the time complexity could be O(nlogk).

**Solution 6**

- Construct a ~~min heap~~ ==(pq)==which could contained k element and a result array which length is m * k.
- Add the first elements ==(head)== in every array into the min heap. While heap is not empty, add the element produced by remove_min() into the result array and ~~add a new element in the arrays (from the second one)~~ add the next element ==from the array where the min is removed from if this array is not pointed at the end==.

[ correct ] use red

- ==I use a priority queue to store the pointers to positions in the lists where the priority is the value this position hold.==
- ==Firstly, add the head of all lists with its related value into the priority queue.==
- ==While the priority queue is not empty:==
    - ==add the pointer produced by remove_min() and add the next pointer from the list where the min is removed from if the list has next position to point.==

# 6

**solution 5**

- Augment an additional doubly linked list with the original hash table data structure.
- Augment put(k, v):
    - If k is not in the hash table:
        - Do the original operations and add the key into the doubly linked list.
    - If k is in the hash table:
        - Do the original operations. Remove the key in the doubly linked list. ~~Let the keys after the key removed moved forward for one step to fulfill the space produced by the removed key.~~ ==因为是doubly linked list所以其实是不需要这样做的。==Add the key into the doubly linked list.
- Augment get(): nothing to add.
- Augment delete():
    - If k is in the hash table:
        - Do the original operations. Remove the key in the doubly linked list. ~~Let the keys after the key removed moved forward for one step to fulfill the space produced by the removed key.~~ ==因为是doubly linked list所以其实是不需要这样做的。==
- entries():
    - Iterate the doubly linked list and in every iteration add (the current value, get(current value)) into the result list.
- keys():
    - Iterate the doubly linked list and in every iteration add the current value into the result list.
- values():
    - Iterate the doubly linked list and in every iteration add get(current value) into the result list.

**Solution 6**

- Construct a hashtable ~~which could contain n integers~~ ==which could contain 2n== **==entries==**.
- Iterate the array. In every iteration:
    - use get(current element) to test if the current element is already in the hash table. If the function return an actual value, then let x_cur be equal to the value of get(current element) plus one and call put(current element, x_cur).
    - If the function return null which means the current element is not in the hash table. Then call put(current element, 1).
- Iterate the entries() which return the iterable collection of the entries in the hash table. let the max number be the first key in entries. In the every iteration, compare the value of the current key with the value of the max number. If greater than the the value of the max number, the max number becomes to the current key; else, do nothing.
- While load factor is smaller or equal to 1/2, the operation takes O(1) time.

**Solution 7**

Keep a hash table which key is key k and value is a linked list.

- put(k, f): If h(k) is exist in the hash table, then append the f into the existing linked list. Else, create a linked list which head is f. Then use hash table’s put(k, the linked list).
- get(k): Use the hashtable’s get(k) which could get a linked list if k is existing. Then iterate the linked list to add it to the result array which may takes O(s) time.

**Solution 8**

[ Only work out a O(n) method ]

Keep a hash table which ~~key is the date of birthday~~ ==map the birthday date into a==nd value is a linked list which contains names of people whose birthday date is the key. ==Map the birthday to a number in [1, 365] and use a hash function on integers to build a hash table of size 2 ∗ 365 using linear probing.==

Iterate the hash table to find if there is key whose value which is a linked list contain two names. If so, return the value of this key which is the pair that shares the birthday. Else, return null becasue there is no such pairs exists.

==[ O(1) method ]==

方法居然是一样的 但是写法有一定的差异

**Solution 9**

use same as the solution 6. But we need to use 2n sized hashtable which key is the type of the k-grams to get the desired time complexity.

# 7

**solution 1**

a) L0: A

L1: B E

L2: C H F

L3: D I G

b) A B C D ~~E H I F G~~

1. _A_,_B_,_C_,_D_,==_H_====,====_E_====,====_F_====,====_G_====,====_I_==

**solution 2**

<理解>

bipartite：一个图里的点可以分成两个集合，且每个集合中的点互无edge相连。

设计一个algorithm 去检验这个图是否是bipartite。

a)

I can discuss this by cases.

if i = j, then I done.

Otherwise, assume without the loss of generality that u is discovered first than the v which means i < j. When processing u, BFS algorithm will scans the neighbours of u which contains v. Since v is ended up in the Lj, and j is bigger than i, which means v is not seen yet when I discover the u. However, this step means the v will be placed into the next layer because (u, v) means that v is one of the neighbour of u. Therefore, j = i + 1, then that is true.

b)

If u, v at a same layer and there is an edage (u, v), there is an odd cycle which made by the path from the root to u, edage (u, v), the path from the v to root. However if a graph is a bipartite graph, the cycle in this graph should only be an even cycle. Therefore, this graph is not a bipartitr graph.

c)

In a bipartite graph which is scanned by BFS, the odd layers belong to the set A and the even layers belong to the set B. And the set A and set B is equal to the V of this bipartite graph. Because there is no any intra layer edges, therefore the edges only connect between the set A and set B, which satisfys the definition of bipartite graph.

  

d) Use BFS to return layers of the input graph. And iterate every layers:

In every layers, test if there has a edge made by two nodes in the same layes.

==<少考虑了条件 这个图不一定是connected的 所以说要用DFS来检验是否connected>==

Use DFS to compute the **==connected components==** in this graph, and use BFS to test every connected components if there is a intra layer edges inside. If so, return False or null; else, return the odd layers of the graph and even layers of the graph as sets A and B.

**Solution 3**

I use DFS with some minor modification. Everytime the DFS scan the current node u’s neighbour, it should test this node’s neighbour v has seen before except this node’s parent. If so, there is a cycle in the DFS which is u, parent[u], parent[parent[u]]... v.

The initialization step in DFS takes O(n) time and the DFS’s visit function time complexity depends on how many edge DFS will visit. Before I find a circle, I keep calling the DFS’s visit in a tree structure which has n - 1edges at most. Therefore the visit will takes O(n) time in total. And the part of produce a cycle is also smaller or equal to n time which also takes O(n).

**Solution 4**

I discuss this question by cases.

- If there is only one way between u and v, every node on this path satisfys the requirement.
- If there is more than one way between u and v which means those paths has no same node. Those paths may need nodes which are more than n nodes. Here is a contradiction. Therefore, there must be a u which in on every path from s to t.

[ another way ]

核心思路就是证明通过BFS这里有一个layer只有一个点

  

- Run BFS at s. Because the dist(s, t) > n/2, therefore the t is on the layer j which distance is k which is bigger than n/2.
- I want to prove that here is a layer which has only one node between the layer 0 and layer j. Assume that all layers from s to t (contains s and t) contains two or more layers. If so, the nodes in those layers are more than 2 * n/2 = n nodes which is contradiction. Therefore, this contradiction prove that here is a layer which has only one node between the layer 0 and layer j and the node in this layer is the node on every path from s to t.

**Solution 5**

[ O(n**2) way ]

look through the adjacency matrix structure to find if there is a vertex only has a in-degree no out degree. And test if the in-degree number is n - 1. If so, return this node.

return null.

==[ better way ]==

By observation, if the node i is a get-stuck vertex, the i-th row is all 0, and the i-th column is all 1 except the (i, i).

Therefore, I use a pointer to go down and right in this matrix which obey the following rule:

- If the pointer is at (i, i), then move to right.
- If the pointer is at (i, j) where i is not equal to j, then move down if there is 1 and if there is 0 move right.

At the end of the move, the pointer will exist from the some position in the right side of the matrix. Check if the i- th row where the pointer exits satisys the row is all 0 and the i-th column is all 1 except the (i, i). If so, return this node. Else, return null. The worst case is to move about n times, therefore the time complexity is O(n).

  

**Solution 6**

~~I use a modified DFS to implement the small(i). Every time the DFS scan the current node’s neighbour, it can also find the neighbour which has the least number. therefore this is the small(i)’s value.~~

**==[ connected不是只是neighbor！！！]==**

I use DFS to find the connected components in this graph. In every connected components C, use DFS again to find the node with the smallest value q and every node in C small() may return the q.

  

**Solution 7**

a) Cut edges:

- Leaf nodes cannot be a cut vertex.
- If the root has two or more child, then the root is the cur vertex.
- When in the inner node, if the node’s down-and-up[] value is greater than or equal to level value, this node is the cur vertice.

b) The algorithm will firstly run DFS to calculate the down-and-up value and level of every node. After this, firstly test the root. If the root has two or more child, then the root is the cur vertex. Then use DFS again to test any inner node, if this inner node has a child and this child’s down-and-up value is is greater than or equal to this child’s level value. in this case, this inner node is a cut vertex.

  

**Solution 8**

Perform the post-order traversal on this tree T. In every recursion, the recursive function has another parameter named “min_value” to maintain. For example, the first time calling the recursive function is post_order(u, A[u]) where u is the left most child of T and the “min_value” is the current node’s A value. and in the visit() function, compare the A[currentnode] with the min_value.

- If the min_value is bigger than A[currentnode], then min_value is equal to A[currentnode].

After the comparison, let B[currentnode] be equal to the min_value.

  

**Solution 9**

a) The edges in the DFS tree are all cut edge.

b) see problem 7.

  

# 8

**solution 1**

The only thing to modified is that when update the D[z], make sure there has an edge from u to z. If so, this D[z] can be changed; else, can not be changed.

**Solution 2**

a) ad → cf → ef → de → cb

b) ad → cf → ef → de → cb

c) ad → de → ef → cf → cb

**Solution 3**

a) As we know p is the shortest s-t path which sum weight is w. Then under w1, p’s weight becomes to aw where w is positive and a is also positive. Therefore, p is also the shortest since its weight is also smaller than other aw1, aw2..

p may not be the shortest path for w2. If there is a path which pass less node and the less beta it will gain which may has less weight than p.

  

P ~~is the shortest path for w3.~~

==P is also can be not the shortest path for w3. Consider a circle where is s-w-t. s-w is 0.5, w-t is 0.6 and s-t is 1. Before squre, the shortest path is 1; After squre the shortest path is s-w-t.==

Consider ==**the weight could be less than 1**==

b)Assume that using kruskal to get the MST. T is also the optimal under w1, w2, w3 because the order of adding edges into T havent changed.

**Solution 4**

a) l’Q - l’P = M(lQ) - M(lP) Because the edge length are all positive integer then l(Q) - lP > 1 Then l’Q - l’P > M

b) Assume P using q edges, and Q using p edges. p < q l’’, P = Ml(P) + q ;l’’Q = Ml(Q) + p where Ml(Q) is equal to Ml(P) and q > p. Therefore l’’Q is bigger than l’’P.

c) ~~M should be positive.~~

M should be the number of vertices of this graph.

**Solution 5**

The input is a undirected graph which every node and every edge has weight. Therefore we convert this undirected graph into directed graph by those steps:

- For every node u in the original graph, made a copie of u. In other words, the directed graph has two node uin and uout.
    - Link those two nodes with the node weight. which is uin → uout and edge weight is the node u’s weight.
- For every edge (u, v) in the original graph, turn it become to uout → v in with the (u,v)’s edge weight.

Run the Dijkstra to find the path with the smallest edge and node value sum.

**Solution 6**

Correctness proof: Assume here is a (u, v) which is rejected in the x-th iteration (x can be any iteration in this algorithm). Then this mean (u, v) has a bigger edge weight than other edge which is still in the tree. Because during iterations, the tree size become smaller without any new edge added into, (u, v) is bigger than the edges in T after the iteration rejecting it.

Here is a (q, p) in the final tree. Assume a (u, v) rejected before has less weight than (p, q). But according to the theory I conclude in the first paragraph, that is not true.

Time comple. ity: Using DFS to find a T + e’s circle needs O(n) time because the spanning tree has n-1 edges. Finding the heaviest edge in C also using about O(|C|) = O(n). Therefore, there maybe at most m iterations and the time complexity is O(mn).

**Solution 7**

Correctness:

Suppose e is an edge the MST should keep and T-e is not connected which could be divide into two sets (X, Y). According to the decreasing weight order, all edges in the cut set of (X, Y) must have a bigger edge value than e. In other word, e is the minimum in the (X, Y)’s cut set which has to be in MST according the cut property. Also, we can take a f in (X, Y) cut to form a cycle with e where f is another optimal edge. However this f has a bigger weight value than e which have to be removed and not in MST. That is a contradiction because f is not more optimal than e. Therefore, e must be in MST.

Time Complexity:

Sort edge → O(mlogm)

For loop: O(m + n) to test if connected for m times

O(m ** 2)

**Solution 8**

- Firstly, the all (i, i) (where 1≤i≤n ) in B are equle to the b(vi).
- [ 不会了 ]

==[ answer ]==

==We can form a maximum spanning tree T of G where all edge sum in the T has the maximum value. This tree can be produced by the Kruskal algorithm with the decreasing edge weight values order. After producing this tree T, we can loop paths between every two nodes in this tree T to find the minimum bandwidth in this path. Which may take O(n ** 3).==

  

# 11