Abstract Data Types (ADT)

List

Index-Based Lists (List ADT)

Array-based Lists

Positional Lists

Singly Linked List

Doubly Linked Lists

Summary

Iterators

Linear Data Structure

Stack

Queue

# **Abstract Data Types (ADT)**

1. Type ==defined in terms of its data items== and ==associated operations,== not its implementation. 不会告知你怎么去完成 只告诉你需要完成什么
2. ADTs are supported by many languages, including Python.
3. **Simple example: Driving a car**
    
    implementation是后端的任务 interface是使用者attract with的
    
    ![[/Untitled 34.png|Untitled 34.png]]
    
4. **Benefits of ADT approach**
    1. ==Code is easier to understand== if different issues are separated into different places. 怎么感觉有点像函数？
    2. Client can be considered at a higher, ==more abstract, level==. 不用考虑完成 只使用功能就好
    3. ==Many different systems can use the same library==, so only code tricky manipulations once, rather than in every client system. 可以使用重复代码或者调用 功能不变的情况下也可以改变完成策略使这个功能更加高效
    4. There ==can be choices of implementations with different performance tradeoffs==, and the client doesn’t need to be rewritten extensively to change which implementation it uses.
5. **Example: Reservation system**
    
    其实看上去和java很像 把所有considered as a class
    
    ![[/Untitled 1 4.png|Untitled 1 4.png]]
    
    ![[/Untitled 2 4.png|Untitled 2 4.png]]
    
6. **ADT challenges**
    1. Specify how to deal with the boundary cases– what to do if reserve(x, y) is invoked when x is already occupied?– what other cases can you think of?
        
        invoke - 调用
        
    2. Do we need a new ADT? Could we use an existing one, perhaps by renaming the operations and tweaking the error-handling?
    3. “Adapter” design pattern (see SOFT2201)– Could this example be mapped to an ADT you already know?
        
        在[設計模式](https://zh.wikipedia.org/wiki/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F_(%E8%AE%A1%E7%AE%97%E6%9C%BA))中，**适配器模式**（英語：adapter pattern）有时候也称包装样式或者包装（英語：wrapper）。将一个[类](https://zh.wikipedia.org/wiki/%E7%B1%BB_(%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6))的接口轉接成用户所期待的。==一个适配使得因接口不兼容而不能在一起工作的类能在一起工作，做法是将类自己的接口包裹在一个已存在的类中。==
        
7. **Abstract data types and Data structures**
    1. An abstract data type (ADT) is **==a specification of the desired behaviour from the point of view of the user of the data.==** **这些基本上算是由用户定义的**
    2. A ==data structure is a concrete representation of data==, and ==this is  
        from the point of view of an implementer  
        ==, not a user. 这些是程序员从完成需求上考虑的
    3. Distinction is subtle but similar to the difference between a  
        computational problems and an algorithm. 可以认为ADT是需求，data structure是需求的完成  
        
    4. 一个对应
        1. ADT-computational questions
        2. data structure - algorithm
8. **ADT in programming (Python)**
    1. ADT is given as an _abstract base class (abc)_
    2. An abc ==declares methods (with their names and signatures)== usually without providing code and we can’t construct instances
    3. A data structure implementation is a class that inherits from the abc, **==provides code==** ==for all the required methods (and perhaps others) and has a== ==constructor==
    4. Client code can have variables that are instances of the data structure class and can call methods on these variables 用户代码可以创造实例以及调用方法

# List

## I**ndex-Based Lists (List ADT)**

![[/Untitled 3 3.png|Untitled 3 3.png]]

![[/Untitled 4 3.png|Untitled 4 3.png]]

## **Array-based Lists**

![[/Untitled 5 3.png|Untitled 5 3.png]]

know index and start addr 你就可以算出这个ele的具体位置（这个是相对位移法？？？）

![[/Untitled 6 3.png|Untitled 6 3.png]]

1. The ==get(i)== and ==set(i, e)== methods are easy to implement by ==accessing A[i]==
    
    1. Must **==check that i is a legitimate index (0 ≤ i < n)==**
    2. Both operations can be carried out in the constant time (a.k.a. O(1) time), independent of the size of the array
    
    ![[/Untitled 7 3.png|Untitled 7 3.png]]
    
2. **Pseudo-code for get**
    
    **==Time complexity of this operation is O(1)==** time, independent of the size of the array (N) or the represented list (n)
    
    ```Python
    def get(i):
    # input: index i
    # output: ith element in list 
    	if i < 0 or i >= n then
    	   return “index out of bound”
    	else
    		return A[i]
    ```
    
3. **Pseudo-code for set  
      
    **==**Time complexity of operation is O(1) time**==**, independent of the size of the array (N) or the represented list (n)**
    
    ```Python
    def set(i, e):
    # input: index i and value e
    # do: update ith element in list to e 
    	if i < 0 or i >= 3 n then
    			return “index out of bound” 
    	result ← A[i]
    	A[i] ← e
    	return result
    ```
    
4. In an operation add(i, e), we must make room for the new  
    element by shifting forward n - i elements A[i], ..., A[n - 1]  
    
    1. ==Must check that there is space (n < N)==
    2. What is the most time consuming scenario?
        
        找到新的空的内存
        
    
    ![[/Untitled 8 3.png|Untitled 8 3.png]]
    
    **Pseudo-code → O(n) for find blank**
    
    ```Plain
    def add(i, e):
      if n = N then
         return “array is full”
      if i < n then
    		for j in [n-1, n-2, ... , i] do 
    			A[j + 1] ← A[j]
    	A[i] ← e 
    	n ← n +1
    ```
    
5. **Array-based Lists: remove(i)**
    
    In an operation remove(i), we need **==to fill the hole left at position i  
    by shifting backward n - i - 1 elements A[i + 1], ..., A[n - 1]  
    ==**
    
    ==Must check that i is a legitimate index (0 ≤ i < n)==
    
    ![[/Untitled 9 3.png|Untitled 9 3.png]]
    
    **Pseudo-code**
    
    ```Plain
    def remove(i):
    	if i < 0 or i >= n
    	return “index out of bound” 
    	e ← A[i]
    	   if i < n-1
    	      for j in [i, i+1, ... , n-2] do
    					A[j] ← A[j+1] 
    	n←n-1
    	return e
    ```
    
    **Time complexity is O(n) in the worst case  
      
    ****time consuming: shifting**
    
6. **Summary of (static) array-based Lists**
    1. Limitations:– can ==represent lists up to the capacity of the array (n vs N)==
    2. Space complexity:– space used is _O_(N), whereas we would like it to be O(n)
    3. Time complexity:
        1. both ==get and set take== ==_O_====(1) time==
        2. both ==add and remove take== ==_O_====(n) time in the worst case==

==n 在这里是这个list里面有多少个elements==

## **Positional Lists**

> 链表还是不会 诶 就摆烂

1. ADT for a list where we s==tore elements at “positions”== object
    
    1. Position models the abstract notion of place where a single object is stored within a container data structure.
    2. Unlike index, ==this keeps referring to the same entry even after  
        insertion/deletion happens elsewhere in the collection.  
        ==
    3. Position offers just one method:  
        element() : return the element stored at the position instance  
        
    
    ![[/Untitled 10 3.png|Untitled 10 3.png]]
    
2. ![[/Untitled 11 3.png|Untitled 11 3.png]]
    

### **Singly Linked List**

1. A concrete data structure
2. **==A sequence of Nodes, each with a reference to the next node==**
3. List ==captured by reference (head) to the first Node==

![[/Untitled 12 3.png|Untitled 12 3.png]]

last node: [last.next](http://last.next) = None

1. Each Node in a singly linked List stores
    1. its element
    2. a link to the next node.
        
        ![[/Untitled 13 3.png|Untitled 13 3.png]]
        
2. **Advice on working with linked structures**
    1. ==Draw the diagram== showing the state.
    2. ==Show a location== where you place carefully each of the instance variables (including references to nodes).
    3. ==小心地去进行赋值==
    4. ==Be careful to== **==step through dotted accesses==**
        1. e.g. [p.next.next](http://p.next.next) (最好不要这样写！！！)
    5. Be careful about assignments to fields
        1. e.g.p.next = q or p.next.next = r
3. **How are linked lists stored?**
    
    有空我就插 诶 （链表极其没有素质）
    
    高情商：会利用空间
    
    低情商：插队没素质
    
    ![[/Untitled 14 3.png|Untitled 14 3.png]]
    
4. **first()**
    
    **first() : return position(地址) of first element (null if empty)**
    
    **==return head==**
    
5. **insertFirst(e)**
    1. **Instantiate a new node** _**x  
          
        **_
        
        ![[/Untitled 15 2.png|Untitled 15 2.png]]
        
    2. **Set e as element of x  
          
        **
        
        ![[/Untitled 16 2.png|Untitled 16 2.png]]
        
    3. **Set x.next to point to (old) head  
          
        **
        
        ![[/Untitled 17 2.png|Untitled 17 2.png]]
        
    4. **Update list’s head to point to x  
          
        **
        
        ![[/Untitled 18 2.png|Untitled 18 2.png]]
        
    5. **What is the time complexity?** ==**O(1)**==
    6. position是什么→reference variable都是指代其地址（actual）elec1601我谢谢你
6. **removeFirst()**
    1. **Update head to point to next node**
        
        ![[/Untitled 19 2.png|Untitled 19 2.png]]
        
    2. **Delete the former first node**
        
        ![[/Untitled 20 2.png|Untitled 20 2.png]]
        
    3. **Time complexity?** ==**O(1)**==
7. **insertBefore(p,e):insert e in front of the element at position p**
    
    ![[/Untitled 21 2.png|Untitled 21 2.png]]
    
    ![[/Untitled 22 2.png|Untitled 22 2.png]]
    
    ![[/Untitled 23 2.png|Untitled 23 2.png]]
    
    **Find the predecessor of x.To find the predecessor of p we need to follow the links from the “head”. Time complexity?**==**O(n)**==**  
    There is  
    **==**no constant-time way to find the predecessor of a node**== **in a Singly Linked List.**
    
    ![[/Untitled 24 2.png|Untitled 24 2.png]]
    

### **Doubly Linked Lists**

1. A very natural way to implement a positional list is with ==a doubly-linked list==, so that it is easy/quick to find the position before.
2. Each Node in a Doubly Linked List stores
    1. – its element
    2. – ==a link to the previous and next nodes.==
        
        ![[/Untitled 25 2.png|Untitled 25 2.png]]
        
3. A concrete data structure
    1. A sequence of Nodes, each with reference to prev and to next
    2. **==List captured by references to its Sentinel Nodes==**
        
        - **==有俩个：header & trailer（没有值store inside）==**
        
        ![[/Untitled 26 2.png|Untitled 26 2.png]]
        
4. **insertBefore(p,e)  
    pointer in python → just as addr  
    **
    
    ![[/Untitled 27 2.png|Untitled 27 2.png]]
    
    ![[/Untitled 28 2.png|Untitled 28 2.png]]
    
    Insert a new node with element e ==between p and its predecessor.==
    
    ![[/Untitled 29 2.png|Untitled 29 2.png]]
    
    ```Plain
    def insert_before(pos, elem):
        // insert elem before pos
        // assuming it is a legal pos
    	new_node ← create a new node 
    	new_node.element ← elem 
    	new_node.prev ← pos.prev 
    	new_node.next ← pos 
    	pos.prev.next ← new_node 
    	pos.prev ← new_node
    	return new_node
    ```
    
5. **remove(p)  
    Remove a node, p, from a doubly-linked list.  
    **
    
    ![[/Untitled 30 2.png|Untitled 30 2.png]]
    
    ```Plain
    def remove(pos):
    	// remove pos from the list
       // assuming it is a legal pos
    	pos.prev.next ← pos.next
    	pos.next.prev ← pos.prev
    	return pos.element
    ```
    
    这个怎么有点像pop
    
6. A (doubly) linked list can perform all of the accessor and update operations for a positional list ==in constant time.==
    1. Space complexity is O(n)，==Time complexity is O(1) for all operations==
        
        ![[/Untitled 31 2.png|Untitled 31 2.png]]
        

## Summary

1. Arrays
    1. good match to index-based ADT
    2. caching makes traversal fast in practice
    3. no extra memory needed to store pointers
    4. allow random access (retrieve element by index)
2. Linked List
    1. good match to positional ADT
    2. efficient insertion and deletion
    3. simpler behaviour as collection grows
    4. modifications can be made as collection iterated over
    5. **==space not wasted by list not having maximum capacity==**

caching→ **缓存 (Cache)**

【问题】：Although sigly and doubly linked lists’ space complexity are both O(n), is a doubly linked list need more space than a sigly linked list which contain same elements?

==如果不考虑constant那就是一样的==

==考虑就不一样==

## **Iterators**

1. **Abstracts the process of** ==**stepping through a collection of elements one at a time**== **by extending the concept of position**
    1. Implemented by maintaining a cursor（指针） to the “current” element
    2. ==Two notions of iterator:==
        1. snapshot freezes the contents of the data structure 快照冻结数据结构的内容  
            (unpredictable behaviour if we modify the collection)  
            
        2. dynamic follows changes to the data structure  
            (behaviour changes predictably)  
            
            动态跟随数据结构的变化  
            （行为变化可预测）  
            
2. **Iterators in Python**
    
    1. iter(obj) ==returns an iterator of the object collection==
    2. To make ==a class iterable== **==define the method __inter__(self)==**
    3. The method **==__iter__() returns an object having a next() method==**
        1. Calling next() returns
            1. ==the next object and advances the cursor== or
            2. ==raises StopIteration()==
    
    ```Python
    for x in obj:
       // process x
    Is equivalent to:
    it = x.__iter__()
    try:
       while True:
          x = it.next()
          // process x
    except StopIteration:
       pass
    ```
    

# Linear Data Structure

1. These ADTs are restricted forms of List, where **==insertions and  
    removals happen only in particular locations==**:
    1. stacks follow **==last-in-first-out (LIFO)==**
    2. queues follows **==first-in-first-out (FIFO)==**
2. So why should we care about a less general ADT?
    1. operations names are part of computing culture
    2. numerous applications
    3. simpler/more efficient implementations than Lists

## Stack

1. ADT
    1. Main stack operations:
        1. push(e): inserts an element, e
        2. pop(): removes and returns the last inserted element
    2. Auxiliary(辅助的) stack operations:
        1. top(): returns the last inserted element without removing it
        2. size(): returns the number of elements stored
        3. isEmpty(): indicates whether no elements are stored
2. **Stack Example**
    
    ![[/Untitled 32 2.png|Untitled 32 2.png]]
    
3. **Applications**
    1. Direct applications
        1. Keep track of a history that allows undoing such as Web browser history or undo sequence in a text editor
        2. Chain of method calls in a language **==supporting recursion==**
        3. Context-free grammars 上下文无关文法
    2. Indirect applications
        1. Auxiliary data structure for algorithms
        2. Component of other data structures
4. **Method Stacks**
    1. The runtime environment keeps track of the chain of active methods with a stack, thus allowing **==recursion==**
    2. When a method is called, the system pushes on the stack a frame containing还是谢谢你elec1601
        1. **==– Local variables and return value==**
        2. **==– Program counter==**
    3. When a method ends, we pop its frame and pass control to the method on top

![[/Untitled 33 2.png|Untitled 33 2.png]]

1. 经典题目:**Parentheses Matching**
    1. Each “(”, “{”, or “[” must be paired with a matching “)”, “}”, or “]”
        1. – correct: ( )(( )){([( )])}
        2. – correct: ((( )(( )){([( )])}))
        3. – incorrect: )(( )){([( )])}
        4. – incorrect: ({[ ])}
        5. – incorrect: (
    2. Scan input string from left to right:
        1. – If we see an opening character, push it to a stack
        2. – If we see a closing character, pop character on stack and check that they match
    3. 相关题目链接：
2. **Stack implementation based on arrays**
    
    ![[/Untitled 34 2.png|Untitled 34 2.png]]
    
    1. – Array has capacity ==N==
    2. – Add elements from left to right
    3. – **==A variable t keeps track of the index of the top element==**
        
        ![[Untitled 35.png]]
        
    4. – The array storing the stack elements may ==become full==. A push operation will then either ==grow the array== or ==signal a “stack overflow” error.==
        
        ![[Untitled 36.png]]
        
3. Performance
    1. The space used is ==O(N)==
    2. Each operation runs in time ==O(1)==
4. Qualifications：Trying to push a new element into a full stack ==causes==
    1. ==an implementation-specific exception== or
    2. Pushing an item on a full stack causes ==the underlying  
        array to double in size, which implies each operation  
        runs in  
        ====_O_====(1) amortized time (still O(n) worst-case).==
        1. O（n）是因为你创造了一个新的两倍大的array你需要把原array里的所有东西转移到新array里（之前java写过）

## **Queue**

1. ADT
    1. Main queue operations:
        1. enqueue(e): inserts an element, e, at the end of the queue
        2. dequeue(): removes and returns element at the front of the queue
    2. Auxiliary queue operations:
        1. first(): returns the element at the front without removing it
        2. size(): returns the number of elements stored
        3. isEmpty(): indicates whether no elements are stored
    3. **==Boundary cases:  
        Attempting the execution of dequeue or first on an empty queue signals an error or returns null 尝试在空队列上执行 dequeue 或 first 会发出错误信号或返回 null  
        ==**
2. example
    
    ![[Untitled 37.png]]
    
3. **Queue applications**
    1. Buffering packets in streams, e.g., video or audio
    2. Direct applications
        1. Waiting lists, bureaucracy（官僚？）
        2. Access to shared resources (e.g., printer)
        3. Multiprogramming
    3. Indirect applications
        1. Auxiliary data structure for algorithms
        2. Component of other data structures
4. **Queue application: Round Robin Schedulers（循环制）operating system去执行multitask（渣男cpu进行多进程）**
    
    1. Implement a round robin scheduler using a queue Q by repeatedly performing the following steps:
        1. e ←Q.dequeue()
        2. Service element e
        3. Q.enqueue(e)
    
    ![[Untitled 38.png]]
    
5. **Queue implementation based on arrays**
    
    1. Use an array of size N in a circular fashion
    2. Two variables keep track of the front and size
        1. ==start : index of the front element==
        2. ==end : index past the last element==
        3. ==size : number of stored elements==
    3. These are related as follows **==end = (start + size) mod N (N是array的长度)==**, so we only need two, ==start and size==
        1. 这么算的原因是……：
            
            ![[Untitled 39.png]]
            
    
    ![[Untitled 40.png]]
    
6. **How to get in a wrapped-around configuration**
    
    1. Enqueue N elements
    2. Dequeue k < N elements
    3. Enqueue k’ < k elements
    
    ![[Untitled 41.png]]
    
7. **Operation: Enqueue**
    1. ==Return an error if the array is full==.
    2. Alternatively, we could grow the underlying array as dynamic arrays do
        
        ![[Untitled 42.png]]
        
8. **Operation: Dequeue**
    
    1. Note that operation ==dequeue returns error if the queue is empty==
    2. One could alternatively signal an error
    
    ![[Untitled 43.png]]
    
9. Performance: **Queue implementation based on arrays**
    1. The space used is ==O(N)==
    2. Each operation runs in time ==O(1)==
10. quiz：wk1-wk2 programming exercise → Edstem