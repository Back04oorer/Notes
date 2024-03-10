1

2

9

# 1

**problem 3**

a) In the first iteration, the line 3 will print one time “*”. In the second iteration, the line 3 will print two times “*”… In the nth iteration, the line 3 will print n times “*”. Therefore in this function, “*” will be printed 1 + 2 + 3 + 4 + .. + n times which is smaller than n * n times. Therefore this running time is less than O(n ** 2).

==b)== Assume the n should be odd. To lowerbound the running time, we consider the number of star will be printed during n/2 iterations. Because theses n/2 iterations is a part of the total iteration, therefore the running time of this n/2 iteration can only be the lower bound of the original program. In these n/2 iteration, “*” will be printed 1 + 3 + 5 + … + (n/2 + 1)(assume n/2 is an integer) which is smaller than n/2 * n/2. Therefore the lower bound is Ω(n ** 2/ 4) = Ω(n ** 2).

**problem 4**

This algorithm is incorrect. According to the lecture, there can be negative numbers in the Array A. Therefore for 0 ≤ p ≤ q< _n, there can be a B[p] is bigger than B[q]. The min of Array B is B[i] and the max of Array B is B[j]. i could be bigger than than j which can not obey the_ 0 ≤ i ≤ j< _n. Therefore this algorithm is incorrect._

**Problem 5**

a) The number of iterations in the nested for loop is n + n - 1 + n - 2 + … + 1 = [(n + 1) * n] / 2 which ==is bounded by n ** 2.== For line 5 to compute the average entries of A[i: j] can take O(j - i + 1) = O(n) to complete and O(1) to store. Therefore the total time complexity is O(n) * n ** 2 = O(n ** 3).

b) By looking through the implementation of this algorithm, line 5 is calculate by for loop. When i > n/4 and j > 3n/4, this for loop will iterate at least n/2 times which takes Ω(n) time. There are (n ** 2)/ 16 pairs of [i, j] therefore the total running time is (n ** 2)/16 * Ω(n) = Ω(n ** 3).

**Problem 6**

- Create an Array A_sum whose length is n. The i-th entry store the sum value of the first i elements in Array A(index starts from 0).
- Create a new matrix C to store results. Create a for loop to iterate i from 0 to n - 1.
    - In every iteration of this for loop, create another loop to iterate j from i to n - 1.
        - In every iteration of the loop of k, assign C[i][j] with the value of (A_sum[j] - A_sum[i]) / (j - i + 1).
- Return the C as the result matrix.

**Problem 7**

Proof:

- Because _f_ (_n_) = _O_(_g_(_n_)), T(f(n)) ≤ g(n).
- Because _g_(_n_) = _O_(_h_(_n_)), T(g(n)) ≤ h(n).
- Therefore, T(f(n)) ≤ T(g(n)) ≤ h(n). T(f(n)) ≤ h(n), so _f_ (_n_) =  
      
    _O_(_h_(_n_)).

**Problem 8**

The Line 2 and line 7 is variable assignment and return statement which only takes O(1) each. There are n iteration in the line 3’s for loop. In the first iteration, only line 6 will be executed and j is 1 right now. In the second iteration, line 4’s while loop is executed once and j is 0 after running line 5. After run line 6 j becomes to 1 which is same as the first iteration. Remaining iterations are same as the second one. And the complexity of while loop is O(1) because it is always executed once. Line 6 is variable assignment which is O(1) too. Therefore the total time complexity of Line 4 - 6 is n * 2 *O(1) = O(n). The total running time of this code is O(n) + O(1) + O(1) = O(n)  
  
**Problem 9**

- Make two new variable to record. The Variable cons_len is assigned with 1. The variable cons_chr is assigned with the first character of string s.
- Iterate every character in the string except the first one.
    - In every iteration, compare the current character with the cons_chr.
        - If current character is not equal to cons_chr, then assign cons_len with 1 and assign cons_chr with the current character.
        - Else, if current character is equal to cons_chr, cons_len is equal to cons_le plus 1.
- After this iteration, compare the cons_len with k.
    - If cons_len is equal to k, then return True as the result bolean.
    - Else, return False.

**Problem 10**

<这个index有点昏>

Iterate i from 0 to n - 1. In every iteration of i, if i is smaller than n - 1, iterate j from i + 1 to n - 1. In every iteration of j, test if the i-th element is equal to the j - th element. If so, return True. At the end of the iteration of i, return False. The worst case of this algorithm is that there is no duplicate and we need to iterate n - 1 + n - 2 + … + 1 = [(n-1 + 0) * n] / 2 which is smaller than n * n. Therefore the time complexity is O(n * n) = O(n ** 2)

# 2

**Problem 1**

pop() → O(n) push → O(n)

pop → O(n) push → O(n)

**Problem 2**

enqueue → O(1) dequeue → O(n) or …

**Problem 3**==I think I give a wrong answer.==

a)

- Use a variable prev to store the first node of the linked list. Set the first node’s next pointer as None.
- Iterate the linked list from the second element if the linked list’s length is bigger than 1. In every iteration, set the current node’s next pointer as prev and set the prev with the value of the current node.
- Because this instruction iterate all the list and in every iteration operations take O(1) time, the time complexity is equal to n * O(1) = O(n).

==b)==

- Divede the linked list into $\sqrt(n)$﻿ parts. store every part’s first element as prev_1, prev_2, prev_3... Iterate each part. In every iteration, set the current node’s next pointer as prev_x and set the prev_x with the value of the current node. The time complexity is O(n).

**Problem 4 + Problem 5**

没有读懂题at first：Example: input: 3 output →

```Plain
1 2 3
2 1 3
3 1 2
1 3 2
2 3 1
3 2 1
```

a) Recursive Approach

![[/Untitled 45.png|Untitled 45.png]]

- Write a recursive function called generate(array_in, level_index).
    - Firstly, test the value of level_index and the length of the array_in. if level_index is equal to the length of array, print the array_in.
    - Secondly, iterate the number j from i to n (not include n). Swap the array_in[i] and array_in[j]. Call generate(array_in, level_index + 1). Swap the array_in[i] and array[j] again.
- Write a function called generate_rec(n), in this function generate the array A which invovles numbers of 1, 2,…,n. Call generate(A, 0).
- ~~吗的我考场上咋想的出来啊~~

**==b) Stack Approach==**

**Problem 6**

- Create two stack which are content_stack and sub_stack. Content_stack involves all elements of the queue.
- enqueue(obj): Call content_stack.push(obj) in this function. According to the slide, the time complexity is O(1).
- dequeue: While the content_stack is not empty, call sub_stack.push(content_stack.pop()). After the while loop, call sub_stack.pop(). Then do another while loop. While the sub_stack is not empty, call content_stack.push(sub_stack.pop()). The pop() and push() only takes O(1) time and in two while loop there are n + n - 1 iteration which can be bounded by n. Therefore the total time complexity is O(1) + O(n) = O(n).

**Problem 7**

a) getAverage()：

- Add sum attribute to the queue class and update this attribute in the dequeue and enqueue function. In the dequeue function, the value of sum is equal to the original sum minus the element’s value dequeued. In the enqueue function, plus the value of the element enqueued to the value of sum.
- In getAverage() function, return the value of sum / the value returned by size().

b) According to the definition of the average, we can have a correct return value if we maintain sum correct. We add the new element to sum when enqueue and substract the element removed when dequeue, so we maintain sum correctly.

c) There is only a returning statement inside, so the time complexity is only O(1).  
  

# 9

==**Problem 2**==

1)

**Problem 3**

This algorithm can not always produce the correct answer. Here is a counterexample which A is {3, 8} and B is {1, 4}. Then the result should be {3, 4} and {8, 1} which the value of ∑ |_ai_−_bi_| is 8. However, when the result is {4, 8} and {1, 3} the value should be the min which is 6.

==**Problem 4 → Proof of correctness**==

This algorithm can always produce the correct answe. To prove the correctness and optimality of this algorithm, I use the exchange argument.

- Assume here is a optimal algorithm and a* and b* is the result produced by this algorithm. Because the original algorithm’s a and b is sorted in a non-decreasing order, a[i] ≤ a[i + 1] and b[i] is smaller than b[i + 1]. We can assume a*[i] is also smaller than a*[i + 1] and it does not affect the result.
- If there is a inversion in b where b*[i] > b*[i + 1], I need to prove that |a*[i] - b*[i]| + |a*[i] - b*[i]| ≥ |a*[i] - b*[i + 1]| + |a*[i+1] - b*[i]| which is equal to prove |a*[i] - b*[i]| + |a*[i + 1] - b*[i + 1]| - |a*[i] - b*[i + 1]| - |a*[i+1] - b*[i]| ≥ 0.
    - Here is a proof. I discussed this by cases. In all case **a*[i] ≤ a*[i + 1] and b*[i] > b*[i + 1].**
    - Firstly, a*[i] ≥ b*[i] and a*[i + 1] ≥ b*[i + 1] which can imply that a*[i + 1] ≥ a*[i] ≥ b[i] > b*[i]. Therefore the original left side of the equation is equal to a*[i] - b*[i] + a*[i + 1] - b*[i + 1] - a*[i] + b*[i + 1] - a*[i+1] - b*[i] which is equal to zero.
    - Second case: b*[i + 1] < a*[i]≤a*[i + 1] ≤ b*[i]. The left side of the equaltion is equal to b*[i] - a*[i] + a*[i + 1] - b*[i + 1] - a*[i] + b*[i + 1] - b*[i] + a*[i + 1] which is equal to 2(a*[i + 1] -a*[i]). The left side is greater or equal to zero.
    - Thrid case: a*[i] ≤ b*[i + 1]< a*[i + 1] ≤ b*[i]. The left side of the equaltion is equal to b*[i] - a*[i] + a*[i + 1] - b*[i + 1] - b*[i + 1] + a*[i] - a*[i+1] + b*[i] which is equal to zero.
    - Final case: a*[i] ≤ a*[i + 1] ≤ b*[i + 1]< b*[i]. The left side of the equation is equal to b*[i] - a*[i] + b*[i + 1] -a*[i + 1] - (b*[i + 1] - a*[i] +b*[i] -a*[i + 1] ) which is also equal to zero.
- Therefore for all case this can be proved. By swapping all inversions the result of optimal can be converted to the result of current algorithm which means the current algorithm is not less optimal than the optimal solution. The current algorithm is optimal.

==**Problem 5**==

Prove this by induction. When i = 1, C = c1 with frequecy f1 = 2.

**Problem 6.**

- Sort the set of points in the increasing order. Create an array named intervals_ls to store all unit_length interval. Insert a new interval which start from the first points’ value into interval_ls.
- Iterate the points set from the second point. In every iteration, compare the current point’s value with the last interval’s ending value in interval_ls. If the current point’s value is bigger than the ending value, interval_ls add a new interval which starts from the current point’s value.
- After the iteration, return the interval_ls.

**Problem 7**

- Calculate every job’s ratio of wj / tj and store (j, ratios) into a new array called ratio array.
- Sort the ratio array by every element’s ratio value (the second element in tuple) in non-increasing order.

<看一下为什么是optimal的证明>