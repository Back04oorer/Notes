
## Q4
We have all edges $E_T$,$E_S$ for T and S. For a edge t_i in $E_T$ , if it exists in $E_S$, we do nothing and pick another edge in $E_T$, otherwise, we remove it form T, and iterate all edges in $E_S$ and do following steps:
we add the edge in E_s to T, run DFS to check if T has a cycle. If it does, we remove it and add another one into T and repeat the same process. Otherwise, we record the current T as a tree in sequence.
For all edges in $E_S$ , we perform the above process, all recorded trees perform a sequence as required.




# Wechat

## Q4
a) We sort the array first in increasing order. We initialise 2 pointers that points to the index 0(start of array) and a variable x to store the number of combinations .If i  equals to j, we make j = j +1 (go right once). If A_j - A_i <= k, we let x = x + 1 and let j+=1. Otherwise, we make let i+=1.
We repeat above process util i, j reach the end of the array.

b) we use an invariant to proof the correctness of my algorithm:
	x correctly stores all combinations (x,y) that x <= i, y < j
In the initialisation step, i = j = 0 , x = 0, there will not be any valid combination in this case, the invariant holds.
When i = j , we simple make j =j+1 ,the variant hold because when i=j  (i,j) is not a valid combination.
When A_j - A_i <= k which means this is a valid combination, we correctly counts it and all valid combinations have been counted for x <= i, y < j.Thus the invariant holds
When A_j - A_i > k which means that this is not a valid combination, we reduce the distance by increment i by 1. The ensure us to find all valid combinations that y < j . Thus the invariant holds.
Therefore , the invariant holds during the loop.The algorithm is correct.


