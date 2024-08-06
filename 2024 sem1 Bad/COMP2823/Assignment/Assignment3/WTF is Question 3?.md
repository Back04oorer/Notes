
```PYTHON
class AVLNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.height = 1
        self.size = 1
        self.left = None
        self.right = None
```

```PYTHON
function updateHeight(node):
    node.height = 1 + max(height(node.left), height(node.right))

function updateSize(node):
    node.size = 1 + size(node.left) + size(node.right)

function height(node):
    if node is None:
        return 0
    return node.height

function size(node):
    if node is None:
        return 0
    return node.size

function getBalance(node):
    if node is None:
        return 0
    return height(node.left) - height(node.right)
```


The general idea is 为了在 log(n) 的时间内完成插入和删除操作，我们设计了四棵 AVL 树来优化主菜和配菜的数据管理。这四棵树分别是 `PriceMainTree`、`PriceSideTree`、`NameMainTree` 和 `NameSideTree`。

首先，`PriceMainTree` 和 `PriceSideTree` 以菜肴的价格作为键（key），每个价格对应的菜肴数量作为值（value）。当执行 `addNewMainDish(name, price)` 和 `addNewSideDish(name, price)` 方法时，我们首先检查是否存在相同价格的节点。如果存在，我们仅增加该节点的菜肴数量（`DishNum`）。如果不存在该价格的节点，我们新增一个节点并执行树的重新平衡。

在删除操作中，由于输入是菜肴的名称，我们无法直接在基于价格的树中找到对应节点。因此，我们使用 `NameMainTree` 和 `NameSideTree` 来存储菜肴名称与价格的映射关系。这两棵树以菜肴名称为键，价格为值，节点间比较也基于字典序。进行删除操作时，比如对于主菜，我们首先在 `NameMainTree` 中查找到对应的价格，然后删除该名称的节点并重新平衡 `NameMainTree`。接着，我们使用查找到的价格作为键，在 `PriceMainTree` 中找到相应节点。如果该节点的菜肴数量（`DishNum`）为 1，我们将其移除并重新平衡树；如果大于 1，则减少数量并更新树。

此外，这些 AVL 树的节点还维护了一个额外的属性`DishSize`。这个属性不仅记录了该节点的菜肴数量，还包括其子树中所有节点的菜肴总数。在执行添加、删除或树的重新平衡操作时，这个属性都会相应更新，以保持数据的准确性和树的平衡。

为了能在O(1)时间内countCombinations(),找到小于等于15元的主菜加配菜的组合.我们维护一个全球变量`Combinations`来记录,在插入时,以插入main dish为例,我们在执行完上述的操作后,从`PriceMainTree`中找出小于等于$(15-price(main\_dish))$的最大node,计算出小于等于这个价格的dish数量,`Combinations`中加入 此数量,代表了新加入的主菜和side能搭配出的所有合法的combination的数量.在Remove操作中,也是如此,最后从`Combinations`中减去被删除的主菜和side能搭配出的所有合法的combination的数量.

给出的伪代码详细解释了PriceMainTree 和 PriceSideTree 是怎么工作的,在AVL树的基础上,通过以下方式来维护`dish_size`属性:

    When a new node is inserted into the AVL tree, it potentially impacts the \texttt{dish\_size} of all nodes along the path from the insertion point to the root, as these nodes indirectly include the new dish through their subtrees. Post-insertion, after each insertion, a backtrack from the point of insertion to the root updates each node's height (\texttt{UpdateHeight}) and \texttt{dish\_size} (\texttt{UpdateDishSize}). The \texttt{dish\_size} is recalculated as the current node's \texttt{dish\_count} added to the \texttt{dish\_size} of its left subtree (if it exists) and right subtree (if it exists).

    Deletion is more complex than insertion as it might involve reconnecting subtrees, especially when deleting a node with two children. Post-deletion, all nodes from the deletion point to the root need their \texttt{dish\_size} updated since the structural changes could affect the subtree configurations. Special case handling involves finding the minimum node from the right subtree to replace the deleted node, requiring updates to the \texttt{dish\_size} from this substitute node back to the original node's position and up to the root.

    Rotations, both left and right, are key to maintaining the balance in an AVL tree and affect the subtree connections of a few nodes. After each rotation, the \texttt{dish\_size} of the affected nodes must be updated. For a right rotation, update the original root (\texttt{y}) and the new root (\texttt{x}); similarly for a left rotation. The update method involves updating the node's \texttt{dish\_size} to its own \texttt{dish\_count} plus the \texttt{dish\_size} of its left and right subtrees.







`FindAndAccumulateDishes(node, price)` 用于从`PriceMainTree`或者`PriceSideTree`中找出价格小于等于$(15-price)$的dish总数量
思路是在查找过程中累加满足条件的节点的 `DishSize`，只在适当的时候加上左子树的`DishSize`。
- 从根节点开始。
- 如果当前节点价格小于等于给定价格，那么：
    - 加上当前节点的`DishNum`和左子树的`DishSize`（如果存在左子树）。
    - 向右子树移动（因为右边可能还有小于或等于该价格的其他节点）。
- 如果当前节点价格大于给定价格，仅向左子树移动（不累加当前节点的`DishNum`或`DishSize`，因为它超出了价格范围）。



基于上述`PriceMainTree`和`PriceSideTree`的定义,以及`NameMainTree` 和 `NameSideTree`仅仅是以name为key的普通avl树,我们可以定义addNewMainDish(name, price),addNewSideDish(name, price,removeMainDish(name),removeSideDish(name)和countCombinations(),不论是add或者remove操作,都会增加或者减少受影响的组合,当需要知道combinations时,直接return变量combinations的值就可以:




# 3B

### AVL tree始终保持平衡
通过每次插入或删除操作后的平衡调整（左旋和右旋）,确保了任何节点的两棵子树的高度差不会超过1。这通过在每次操作后计算平衡因子并应用旋转来实现。


`dish_size` 属性在每个节点上表示该节点及其所有子节点的菜品总数。这一属性的正确维护基于以下几点：

- **插入操作**: 每当一个新的菜品价格被插入到 AVL 树中时，从插入点到根节点的路径上的所有节点的 `dish_size` 都会更新。如果价格已存在，只需增加该节点的 `dish_count`，并递归更新沿途节点的 `dish_size`。这确保了在任何时间点，任何节点的 `dish_size` 都准确地反映了其子树中的总菜品数。
- **删除操作**: 删除一个菜品时，如果其 `dish_count` 大于1，只需将 `dish_count` 减一并更新路径上的 `dish_size`。如果 `dish_count` 等于1，则需要删除该节点并可能需要平衡树。在这个过程中，删除节点或重平衡树都会引起路径上每个节点的 `dish_size` 的重新计算。
    

### 2. 计算小于等于 $(15 - price)$ 的总菜品数量

- **准确性**: 函数 `FindAndAccumulateDishes` 按照 AVL 树的性质进行，从根节点开始，根据价格与 $(15 - \text{price(main\_dish)})$ 的比较来选择搜索路径。在此过程中，每次当当前节点的价格小于或等于这个值时，将当前节点以及其左子树（如果存在）的 `dish_size` 加入到总数中。这确保了所有合适的节点都被计数，而价格高于该阈值的节点则被正确地忽略。
    
- **不重复不遗漏**: 由于 AVL 树的性质和 `FindAndAccumulateDishes` 的遍历策略，任何符合条件的节点都将在搜索过程中精确地被访问一次。左子树的节点在满足条件的节点被计数时一起被加入总数，右子树中仍可能有符合条件的节点，因此继续搜索。这种方法确保了不会重复计数任何节点，也不会遗漏任何符合条件的节点。
    

### 3. `combinations`

- **实时更新**: 在每次插入或删除操作后，`combinations` 变量会根据新插入或删除的菜品可能参与的组合数量进行更新。这样的设计允许系统快速响应查询，提供即时的组合总数，而无需重新计算。
    
- **操作合法性和正确性**: 插入时增加计数、删除时减少计数的逻辑保证了 `combinations` 的当前值总是反映了所有可能的、有效的菜品组合。因为每次操作只关注单个变化点（即插入或删除的那个菜品），所以更新既不会重复也不会遗漏。





