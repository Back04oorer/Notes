Method

Proof of correctness

Time complexity - Recurrence formula

Time complexity - Master Theorem

Knowledge

第二次复习可以重新做的题目

1. 计算lower bound就是smaller for loop size 或者是iteration size lowerbound必须用 Ω
2. 在某一个rank的时间复杂度可以用bounded by xx来。 例如0.5 * n **2 can be bounded by n * * 2
3. Use bullet points。清楚明了。能不写长难句就不写。清楚！先用bullt point把大框架写出来。再加入细节。（实在不行就先用python写个出来）。
4. index有点昏就在纸上画一下再写 不要急。

# Method

1. 树的三种遍历
    1. In-order → 左 根 右 ==且是从最左下开始iterate==
        
        ```Python
        def in_order(v):
        	if v.left != None then
        		in_order(v.left)
        	visit(v)
        	if v.right != None then
        		in_order(v.left)
        ```
        
    2. pre-order → 根 左 右 从树根开始iterate
        
        ```Python
        def pre_order(u):
        	visit(u)
        	for v in u.children:
        		pre_order(v)
        ```
        
    3. post-order → 左右根 ==且是从最左下开始iterate==
2. exchange argument
    1. 假设有一个optimal solution 对比他的output与我算法的output （一般：output有不一致的地方 交换不一致/output有顺序相反的地方 交换顺序相反的地方）
    2. 证明交换之后方法更好/不变
    3. 得出结论：是optimal
3. Recursion function 模版
    1. 调用递归函数（通常是在单个函数中 但是无所谓）
        
        ==Use a recursive (helper) function(that takes [input parameter]== ==as input )==
        
        ![[Screen_Shot_2022-07-03_at_11.30.01_pm.png]]
        
    2. 描述递归函数
        1. 点明是传入什么参数call的When the function is called at some node _u_ in _T_
        2. 递归条件：we ==recursively== compute the size of the left and right subtrees of _u_, add 1, set the result to be the size of the subree at u.
        3. 最后return ：==return the value to be reused further up in the recursion.==
4. Divide and conquer 算法复杂度分析
    
    1. 分别分析三部分的算法复杂度（divide conquer and recur）
    2. 合并如下：
    
    ![[Screen_Shot_2022-06-11_at_8.55.32_am.png]]
    
5. 花费O(1)时间 == takes constant time
6. 写的时候不要总是去描述一个function，写笼统一点也是可以的。
7. 子节点 → descendant 父节点 → ancestor
8. 如何计算每一个node的height
    
    1. manipulated pre_order
    
    ```Python
    def pre_order(u, level):
    	u.depth = level
    	for v in u.children:
    		pre_order(v, level + 1)
    ```
    
    ```Python
    def calc_all_depth(root):
    	pre_order(root, 0)
    ```
    
    - Use modified pre_order (recursive function) to calculate every node’s depth in this tree.
        - Add another parameter named ‘level’ in the recursive function and the visit function is substituted with the assignment which assigns ‘level’ value to u.depth.
        - When recursively call the function on u’s children, where the level parameter is set to one plus u’s level.
    - To calculate all every node’s depth in this tree, just simply call this recursive function on the root of tree with level value being 0.
9. **==Inorder traversal 跑bst的node是从小到大的 （重要 会用在一些找到当前点的最小的孩子之类的方法中）==**
10. successor → 后继者/后继节点
11. replace contents of a with entry from b 交换a与b的值
12. **==Induction:==**
    
    1. Basis Step(Case)
    2. Inductive Hypothesis
    3. Inductive Step(Case)
    
    - 多使用数学证明 inductive同样也是指 y(n) 是 y(n - 1)怎么怎么样得到的 ⇒ 常常在递归里面出现并证明
13. augment 增加 补充
14. bifurcation 分歧 分叉
15. Priority Queue:
    
    1. Unsorted list implementation
        - Insert() → O(1)
        - remove_max()/remove_min(): O(n)
    2. Sorted list implementation
        - Insert() → O(n)
        - remove_max()/remove_min(): O(1)
    
    ![[/Untitled 47.png|Untitled 47.png]]
    
    c. sort_priority_queue() → O(n ** 2)
    
16. Prove Ω() (example: Ω(n ** 2))
    1. prove O(n ** 2)
    2. let input to be n/2
    3. If also O(n**2) then Ω(n ** 2) is true
17. ==用null== 最好不用None
18. Hash table: 在做hash table的这些题目时，**==讨论时间复杂度必须结合load factor==**
    1. Hash table 在创造时必须指明hash table的
        1. size
        2. 使用了什么方法来防止collision （哪怕不会碰撞也要写！）→ separate chaining/linear probing/ Cuckoo hashing
        3. key是什么 （hash function是怎么map key的）
        4. value是什么
        5. Example: Keep a hash table with _==size==_ entries using _==method for avoiding collision==_ where the keys are ____ and the value is _____
    2. 构造的另一种
        1. Map the ==(input)== to a number in ==[range]== and use a hash function on integers to build a hash table of size ==(size number)== using (==method for avoiding collision)==
19. Priority queue:
    1. 指明
        1. 支持的是max remove_max() 操作还是 min remove_min() 操作
        2. content是什么
        3. priority是什么
        4. 例子：keep a priority queue holding ==(values)== where the priority of ==(values)== is ==(keys)==. This priority queue supports ==[max and remove-max/min and remove-min]== operations
20. connected component 图里面的连接起来的部分
21. 使用已经存在的算法但是需要改一下
    1. I use ==(algorithm_name)== with (minor) modification. ==[ discription of the modification. ]==
    2. I use modified ==(algorithm_name)==.
22. 不同的graph的构成模式会造成不同的time complexity。
23. 看到dist一定要想BFS（因为BFS可以返回最小路径）
24. Edge weight could be less than 1
25. 用contradiction!!!
26. Assume for simplicity 为了简洁性做假设……
27. 提高线性数据结构的高效性: add a pointer或者using 一个array to store pointers （总之和pointer沾边）
28. 数量上进行证明：the pigeon-hole principle
29. 不一定要用iterate 用scan也是可以的
30. Divide & conquer：base case可以是 1或者2 等等 一定要在纸上写着想一想
    1. First half of这种的take O（n）去divide → merge sort
    2. A[a:b] 这种就是O（1）→ binary search
31. arbitrary 任意的

## Proof of correctness

1. Invariant证明法: 证明这个algorithm的invariant。
    1. 证明最初是正确的: showing that the invariant holds initially
    2. 证明在过程中（循环或者递归中是正确的）show that the invariant is maintained after processing [iteration/recursion/…]
    3. 证明return的结果是正确的
        1. 如果return是正确的 确实是符合题目要求
        2. 如果return是错误的 确实是符合题目要求
2. Trivial的 直接解释你这个算法在干嘛 所以是正确的

## Time complexity - Recurrence formula

![[/Untitled 1 5.png|Untitled 1 5.png]]

## Time complexity - **Master Theorem**

![[/Untitled 2 5.png|Untitled 2 5.png]]

# Knowledge

1. 欧拉回路: 求一条经过图中所有点且边权和最小的回路。

# 第二次复习可以重新做的题目

tute3-8

tute4-7,8

tute5-6