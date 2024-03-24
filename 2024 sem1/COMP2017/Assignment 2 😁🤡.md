
不能漏啊!!!!再漏就兜不住啦!!!

ASAN
```
gcc -fsanitize=address -g <source .........>
```

Valgrind
```
valgrind --leak-check=full --show-leak-kinds=all --track-origins=yes ./mtll < tests/Nest/Edge/edge_3.in
```

# Tests 
### P1
`tests/Basic/`
#### Positive cases
1. List with length 0, char, etc.:
	`tests/Basic/Positive/positive_1.*`
#### Negative cases
1. Leading/Trailing space when `NEW`:
	- should print `INVALID COMMAND: NEW`
	`tests/Basic/Negative/negative_1.*`
## P2
### Negative cases
1. INVALID INPUTS 1:
	- float argument/consecutive spaces/leading or trailing spaces
	`tests/INSERT_DELETE/Negative/negative_1.*`
## P3
### Negative cases
`tests/Nest/Negative/`
1. Invalid `VIEW-NEST` commands
### Edge cases  
`tests/Nest/Edge/`
1. **Circular Reference Detection**:
    - Create two simple lists, A and B.
    - Embed A into B, then try to embed B into A.
    - Expected result: The second step should return "INVALID COMMAND" because it would lead to nesting beyond one level.
    `tests/Nest/Edge/edge_1.*`

2. **Self-Reference**:
    - Attempt to embed a simple list into itself.
    - Expected result: Should return "INVALID COMMAND" because a list cannot contain a reference to itself.
    `tests/Nest/Edge/edge_2.*`

3. **Indirect Reference Deletion**:
    - If a simple list is deleted but it is referenced by another list, especially when this reference occurs indirectly through another nested list.
    - Expected result: Attempting to delete such a referenced simple list should return "INVALID COMMAND".
    `tests/Nest/Edge/edge_3.*`

4. **Deletion Leading to Simplification**:
    - Check if a nested list correctly becomes a simple list when the only simple list within it is deleted.
    - Expected result: The nested list should convert to a simple list.
    `tests/Nest/Edge/edge_4.*`

5. **Depth Limit** 1:
    - Attempt to create a nested list within another nested list.
	 `tests/Nest/Edge/edge_5.*`

6. **Depth Limit** 2:
    - Attempt to INSERT a reference of list into a list which is a nested list


### Positive cases
`tests/Nest/Positive/`
1. **Multiple References**:
    - A simple list is referenced by multiple nested lists, then modify this simple list.
    - Expected result: All nested lists that reference the simple list should reflect the modification.
    `tests/Nest/Positive/positive_1.in`









EOF when New
EDGE CASE 6

INSERT 的时候 空的 怎么打印
```
#1470 Insert <float> 0 aha?  | solved I/D negative 1
#1461 > {0} is Invalid when insert and NEW | solved

#1416 
```


