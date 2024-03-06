Basic Writing Principal

Examples

# Basic Writing Principal

1. ==a ← b== 把b赋值给a
2. ==for a ← c to b do==
    
    ==instruction==
    
    for循环：从值c开始一直循环到b结束
    
    - 问题：有包含b吗 这个循环 还是和range函数的左开右闭区间一样
3. ==if [logical statement] then==
    
    ==Instruction==
    
    如果statement为真 则执行......
    
4. =={comments}== 这里是写了一个comment 不执行

# Examples

1.

```Python
max ← −∞
for i ← 0 to n − 1 do
	if A[i] > max then
		max ← A[i]
return max
```

1. ```Python
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