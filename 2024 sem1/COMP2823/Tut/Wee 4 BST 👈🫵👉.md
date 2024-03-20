### Q1
Give the full pseudocode for doing a tri-node restructure at x, y, z where x is the left child of y and y is the left child of z.
我tm看不懂题目啊 

```
    z                y
   /                / \
  y      --------> x   z
 /
x
```

```PYTHON
y.right() = z
z.parent() = y
```


### Q2
Consider the implementation of a binary search tree on n keys where the keys are stored in the internal nodes. Prove using induction that the height of the tree is at least $log_2 n$.

<mark style="background: #FF5582A6;">Basic Step</mark>
If $n = 1$, $log_2 1= 0$ True

<mark style="background: #FF5582A6;">Inductive Step</mark>
Assume that for k nodes, the height of the tree is greater or equal to $log_2 k$ 

If the tree with k nodes is a complete BST, add a new node will let the height become $log_2k + 1$.
$log_2k + 1 \geq log_2 (k+1)$

If the tree with k nodes is not a complete BST, add a new node will keep the same height.


### Q3
🤣  Ha?
### Q5
Consider the following operation on a binary search tree: largest() that returns the largest key in the tree. Give an implementation that runs in O(h) time, where h is the height of the tree.

```PYTHON
def helper(root):
	current = root
	while(True):
		if current.right();
			current = current.right()
		else:
			break
```


### Q6
Consider the following operation on a binary search tree: <mark style="background: #FF5582A6;">second largest()</mark> that returns the second largest key in the tree. Give an implementation that runs in O(h) time, where h is the height of the tree.

```PYTHON
def helper(root):
	current = root
	while(True):
		if current.right();
			current = current.right()
		else:
			break
			
	if current.left():
		current = current.left()
		while(True):
			if current.right();
				current = current.right()
			else:
				break
	else:
		return current.parent()
```
