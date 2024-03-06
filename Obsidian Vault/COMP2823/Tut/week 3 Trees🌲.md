### `Pre-order Traversal`
```Python
def PRE_ORDER_TRAVERSAL(root):
	vist(root)
	for child in root.children():
		PRE_ORDER_TRAVERSAL(child)
	return
```

### `In-order Traversal`

```Python
def IN_ORDER_TRAVERSAL(root):
	if root == None:
		return None
	
	if root.left() is not None:
		return IN_ORDER_TRAVERSAL(root.left())
	
	visit(root)
	
	if root.right() is not None:
		return IN_ORDER_TRAVERSAL(root.right())
	
```


### Post-order Traversal
```Python
def POST_ORDER_TRAVERSAL(root):
	for child in root.children():
		POST_ORDER_TRAVERSAL(child)
	visit(root)
	return
```
### Q5

Design an algorithm that given a `binary tree T` and a node u, returns the  
node that would be visited after u in a  `pre-order` traversal. Your algorithm should  
not compute the full traversal and then search for u in that traversal.  

```Python
def Find_Next(Node):
	if Node.left() is not None:
		return Node.left()
  
	if Node.right() is not None:
		return Node.right()

	return helper(Node.parent())

def helper(Node):
	if Node == None:
		return None

	if Node.right() is not None:
		return Node.right()
	else:
		return helper(Node.parent())
```

The algorithm may take up to O(n) time to find the next node.

### Q6

Design an algorithm that given a `binary tree T` and a node u, returns the  
node that would be visited after u in an  
`in-order traversal`. Your algorithm should  
not compute the full traversal and then search for u in that traversal.  

```Python
def Find_Left_most(Node):
	if Node.left() is not None:
		return Find_Left_most(Node.left())
	return Node

def Find_Next(Node):
	if Node.right() is not None:
		return Find_Left_most(Node.right())
	return Find_Next(Node.parent())

Find_Next(Node)
```

### Q7
Design an algorithm that given a `binary tree` T and a node u, returns the node that would be visited after u in a <mark style="background: #FFF3A3A6;">post-order traversal</mark>. Your algorithm <mark style="background: #FFF3A3A6;">should not</mark> compute the full traversal and then search for u in that traversal.

```Python
def Find_Next(Node):
	if Node.parent() is None:
		return None
	
	if Node.parent().left() == Node:
		# current Node is left one
		if Node.parent.right():
			return Post_order_traversal(Node.parent.right())
		else:
			return Node.parent()
	else:
		# current Node is right one, and there is no subtree
		return Node.parent()
```

 
 

<mark style="background: #FF5582A6;">Hint</mark> Node u is the most recently visited node (in the traversal method given in the problem).


