Three abstractions

Pseudocode

Small example

Example computational problem

Naive algorithm

Efficiency

Asymptotic growth（渐近增长） analysis

operations take O(1) time

前面的问题的复杂度分析

Recap

A note on style

A note on pseudocode style

FEIT公开课

# Three abstractions

1. Computational problem: (计算机问题)
    1. defines a computational task
    2. specifies what the ==input== is and what the ==output== should be
2. Algorithm:（算法）
    1. a step-by-step recipe to go from input to output (==what your solution does==)
    2. different from implementation
3. Correctness and complexity analysis:（正确性与复杂度分析）
    1. a formal proof that the algorithm solves the problem (why is what your solution does correct)
    2. analytical bound on the resources it uses

# Pseudocode

1. principals:
    1. Control flow
        1. ==if ... then ... [else ...]==
        2. ==while ... do ...==
        3. ==repeat ... until ...==
        4. ==for ... do ...==
        5. ==**Indentation**== **replaces braces**
    2. Method call (函数？)
        1. method (arg [, arg...])
    3. Return value
        1. return expression

# Small example

1. Computational problem:  
    We are given an array A of integers and we need to return the maximum.（普通的找最大值问题）  
    
2. Algorithm:  
    We go through all elements of the array in order and keep track of the largest element found so far (initially −∞). So for each position i, we check if the value stored at A[i] is larger than our current maximum, and if so we update the maximum. After scanning through the array, return the maximum we found.  
    
3. Optional pseudocode:
    
    ```Python
    max ← −∞
    for i ← 0 to n − 1 do
    	if A[i] > max then
    		max ← A[i]
    return max
    ```
    
    ==一些写法总结==
    
    - ==a ← b== 把b赋值给a
    - ==for a ← c to b do==
        
        ==instruction==
        
        for循环：从值c开始一直循环到b结束
        
        - 问题：有包含b吗 这个循环 还是和range函数的左开右闭区间一样
    - ==if [logical statement] then==
        
        ==Instruction==
        
        如果statement为真 则执行......
        
4. Correctness:  
    We maintain the following invariant（不变的）: after the k-th iteration, max stores the maximum of the first k elements.  
    
    Prove using ==induction==: when k = 0, max is −∞, which is the maximum of the first 0 elements.
    
    Assume the invariant holds for the first k iterations, ==we show that it holds after the (k + 1)-th iteration.== In that iteration we compare max to A[k] and update max if A[k] is larger. Hence, afterwards max is the maximum of the first k + 1 elements.
    
    The invariant implies that after n iterations, max contains the maximum of the first n elements, i.e., it’s the maximum of A.
    
    （用了math induction）
    
    （这个证明可以学一下）
    

# Example computational problem

Motivation:

- we have information about the daily fluctuation（波动，起伏） of a stock price
- we want to evaluate our best possible single-trade outcome
- **==总结一下就是寻找一个时间区间此时买入卖出是最划算的==**

Input:

- an array with n integer values A[0], A[1], ..., A[n − 1]

Task:  
- find indices（目录） 0≤i≤j<n maximizing  
==A[i]+A[i+1]+···+A[j]==

# Naive algorithm

1. High level description:
    1. Iterate over every pair 0≤i≤j<n.
    2. For each compute A[i]+A[i+1]+···+A[j]
    3. Return the pair with the maximum value
2. 伪代码
    
    {}里面的是comment！寄 我看了好久没看懂
    
    ```Python
    curr_val, curr_ans ← 0, (N one, N one) 
    for i ← 0 to n − 1 do
    	for j ← i to n − 1 do
    		{Compute A[i]+A[i+1]+···+A[j]} 
    		s←0
    		for k ← i to j do
    			s ← s + A[k]
    		{Compare to current maximum} 
    		if s > curr_val then
    			curr_val, curr_ans ← s, (i, j) 
    return curr_ans
    ```
    
3. ![[/Untitled 33.png|Untitled 33.png]]
    
    ```Python
    curr_val, curr_ans ← 0, (None, None) 
    {B是一个array 第i位包含了前i个值的和 经典用内存换效率}
    B ← new array of size n+1
    B[0] ← 0
    for i ← 1 to n + 1 do
    	B[i] ← B[i − 1] + A[i − 1] 
    for i ← 0 to n − 1 do
    	for j ← i to n − 1 do
    		{Compute A[i]+A[i+1]+···+A[j]} 
    		s ← B[j + 1] − B[i]
    		{Compare to current maximum}
    		if s > curr_val then
    			curr_val, curr_ans ← s, (i, j) 
    return curr_ans
    ```
    

# Efficiency

1. Definition (first attempt): An algorithm is efficient if it ==runs quickly on real input instances==
2. Not a good definition because it is not easy to evaluate:
    1. ==instances considered==
    2. implementation details
    3. hardware it runs on
3. Our definition should implementation independent:
    1. **==count number of “steps” 算法步数==**
    2. **==bound the algorithm’s worst-case performance 算法的最坏表现==**
4. Definition (second attempt)：An algorithm is efficient if ==it achieves qualitatively better worst-case performance than a brute-force approach==
5. ![[/Untitled 1 3.png|Untitled 1 3.png]]
    
    polynomial → 多项式的
    
    1. This gives us some information about the expected behavior of the algorithm and is useful for making predictions and comparing different algorithms.

# Asymptotic growth（渐近增长） analysis

Let T(n) be the worst-case number of steps of our algorithm on an instance of size n.

1. ==Problem==: figuring out T(n) exactly might be really hard! Also, the fine-grained（深入的） details are not necessarily that relevant.
2. ==Example==: T(n)=4n**2+4n+5, or T(n)=5n**2−2n+100.  
    Which one is best? Do the constants matter (recall, one “step” might take a slightly different time based on implementation or architecture details)?  
    
3. ==Insight==: in both examples, the worst-case number of steps T(n) grew quadratically with n. If n is multiplied by 2, then we expect T(n) to be multiplied by 4.
4. ==More generally==: if T(n) is a polynomial of degree d, then doubling the size of the input should roughly increase the running time by a factor of 2**d.
5. Asymptotic growth analysis gives us a tool for focusing on the terms that make up T (n), which ==dominate== the running time.
6. ==Recap==: We want to analyse T(n), the worst-case number of steps of our algorithm on an instance of size n.
    1. But figuring out T(n) exactly might be very hard (impossible), and also is often “too much information”.
    2. We instead do **==asymptotic growth analysis (Big-Oh notation), which provides a coarser（粗糙的） but sufficient way to summarise==** ==**how T(n) behaves when n increases.**==
7. 梦回离散数学（离散数学 我算法路上的大爹
    
    ![[/Untitled 2 3.png|Untitled 2 3.png]]
    
8. tl;dr: think of those as
    - T(n) = O(f(n)): T(n) is ==**“smaller”**== than f(n) (up to a constant factor)
    - T(n) = Ω(f(n)): T(n) is **==“bigger”==** than f(n) (up to a constant factor)
    - T(n) = Θ(f(n)): T(n) is ==**“equal”**== to f(n) (up to constants factors)
        - 证明Θ(f(n))必须证明Ω(f(n)) & O(f(n)).
9. ==Important==: Asymptotic growth analysis (O(·), Ω(·), Θ(·)) is just a mathematical tool to compare functions when the input n grows. We are using this tool to analyse the worst-case behaviour of our algorithms. ==But asymptotic growth analysis and worst-case analysis are not the same thing (they just go hand in hand)!==
10. Examples of asymptotic growth
    1. Polynomial
        - O(n**c), considered efficient since most algorithms have small c
    2. Logarithmic
        - O(log n), typical for search algorithms like Binary Search
    3. Exponential
        - O(2**n), typical for ==brute force algorithms exploring all possible combinations of elements（总之就是低效率）==
11. Comparison of running times
    
    ![[/Untitled 3 2.png|Untitled 3 2.png]]
    
12. ==Properties of asymptotic growth==
    1. Transitivity:
        
        ![[/Untitled 4 2.png|Untitled 4 2.png]]
        
    2. Sums of functions:
        
        ![[/Untitled 5 2.png|Untitled 5 2.png]]
        
13. Survey of common running times
    
    Let T(n) be the running time of our algorithm.
    
    ![[/Untitled 6 2.png|Untitled 6 2.png]]
    
    quasi-linear 拟线性的
    

# operations take O(1) time

1. Constant time:Running time ==does not depend on the size of the input.==
    1. Assignments (a ← 42)
    2. Comparisons (=, <, >)
    3. Boolean operations (and, or, not)
    4. Basic mathematical operations (+, -, *, /)
    5. Constant sized combinations of the above (a ← (2 ∗ b + c)/4)

# 前面的问题的复杂度分析

1. ![[/Untitled 7 2.png|Untitled 7 2.png]]
    
      
    
2. Let T(n) be the running time of the Naive Algorithm on an instance of size n.
    
    ![[/Untitled 8 2.png|Untitled 8 2.png]]
    
    ![[/Untitled 9 2.png|Untitled 9 2.png]]
    
3. 改进代码的效率提升
    
    ![[/Untitled 10 2.png|Untitled 10 2.png]]
    

# Recap

1. **==Asymptotic growth analysis gives us some information about the worst-case behavior of the algorithm.==** It is useful for making predictions and comparing different algorithms.
2. Why do we make a distinction between problem, algorithm, implementation and analysis?
    - somebody can design a better algorithm for a given problem
    - somebody can come up with better implementation
    - somebody can come up with better analysis

# A note on style

For your assessments, you will have to design and analyse an  
algorithm for a given problem. This always consists of three steps:  

1. **==Describe your algorithm==**: A high level description in English, optionally followed by pseudocode. Never submit code!
2. **==Prove its correctness==**: A formal proof that the algorithm does what it’s supposed to do.
3. **==Analyse its time complexity==**: A formal proof that the algorithm runs in the time you claim it does.
4. Try to model your own solution after the solution published for the tutorial sheets. You are encouraged to use LATEX.

# A note on pseudocode style

What we will be using in this class closely follows the Python  
syntax:  

- Arrays: we use zero-based indexing
- Slices: [i:j:k] is equivalent to Python’s range(i, j, k).
- References: Every non-basic data type is passed by reference

But we will deviate when writing things in plain English leads to easier to understand code.

# FEIT公开课

1. 全学期overview
    
    ![[/Untitled 11 2.png|Untitled 11 2.png]]
    
2. An algorithm is ==**efficient**== if it runs in **polynomial time**; that is, on an instance of size n, it performs **no more than** p(n) steps for some polynomial p(x) = adxd + · · · + a1x + a0.
    1. 记忆点: 多项式时间 ==只要是多项式时间就 efficient.==
        1. 指数就不是非常efficient
3. ==时间复杂度====**只考虑 Worst Case**==**!**
4. 分析复杂度 – 总结
    1. 通常来说, 如果一个循环次数与 input 有关, 就需要乘以一次 O(N) 赋值, 基本数学计算, 常数, 都是 **==O(1)==**
    2. **==但并非看到循环可以直接写是 O(N)==**, 依旧需要分析循环的次数.
        1. 例如: 在一些程序里, 内层循环会更新外层循环的变量.（详见本章的“前面的问题的复杂度分析”）
    3. 本节课的算法设计的最佳值：O(nlogn)与O(n**2)之间
5. 例题（assignment）
    
    ![[/Untitled 12 2.png|Untitled 12 2.png]]
    
    ![[/Untitled 13 2.png|Untitled 13 2.png]]
    
    ![[/Untitled 14 2.png|Untitled 14 2.png]]