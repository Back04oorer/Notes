ASAN
```
gcc -fsanitize=address -g <source .........>
```

Valgrind
```
valgrind --leak-check=full --show-leak-kinds=all --track-origins=yes ./mtll < tests/Nest/Edge/edge_3.in
```

# Tests

## P1 Basic Commands

`tests/Basic/`

### Positive cases

1. List with length,scientific-notaion...:

`tests/Basic/Positive/positive_1.*`

  

### Negative cases

1. Invalid Commands, include leading/trailing space when `NEW`:

- should print `INVALID COMMAND: NEW`

`tests/Basic/Negative/negative_1.*`

  

### Edge casess

1. Operations on empty list:

`tests/Basic/Negative/edge_1.*`

  

## P2 INSERT_DELETE

### Positive cases

1. The functionality of INSERT DELETE (mainly checks the range of the index):

- For a list with length n, the valid index range is [-(n+1),n]

`tests/INSERT_DELETE/Positive/positive_1.*`


### Negative cases

1. INVALID INPUTS:

- float argument/consecutive spaces/leading or trailing spaces/curly brackets

`tests/INSERT_DELETE/Negative/negative_1.*`

  

### Edge cases

1. Insert/Delete on empty list:

`tests/INSERT_DELETE/Edge/edge_1.*`

  

## P3 NESTED-LIST

### Positive cases

`tests/Nest/Positive/`

1. **Multiple References**:

- A simple list is referenced by multiple nested lists, then modify this simple list.

- Expected result: All nested lists that reference the simple list should reflect the modification.

`tests/Nest/Positive/positive_1.in`

  

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

`tests/Nest/Edge/edge_5.*`



```
git log --pretty=format:"%H" | while read commit_hash; do     if git show $commit_hash | grep --color -P "[\x{4e00}-\x{9fa5}]"; then         echo "Found in commit: $commit_hash";     fi; done
```


```
git filter-branch --force --tree-filter 'for file in *; do
    if [ -f "$file" ]; then
        perl -CSD -i -pe "s/[\\x{4e00}-\\x{9fa5}]//g" "$file"
    fi
done' HEAD
```
