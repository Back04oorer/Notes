ASAN
```
gcc -fsanitize=address -g <source .........>
```

Valgrind
```
valgrind --leak-check=full --show-leak-kinds=all --track-origins=yes ./mtll < tests/Nest/Edge/edge_1.in
```

# Tests

## P1 Basic Commands

`tests/Basic/`

### Positive cases

1. `tests/Basic/Positive/positive_1.*` - Tests list functionalities including length, scientific notation, etc.

  

### Negative cases

1. `tests/Basic/Negative/negative_1.*` - Tests for invalid commands, specifically checking for leading/trailing spaces with `NEW`.

  

### Edge cases

1. `tests/Basic/Negative/edge_1.*` - Tests operations on an empty list.

2. `tests/Basic/Negative/edge_2.*` - Tests for empty input.

3. `tests/Basic/Negative/edge_3.*` - Tests EOF when using `NEW`.

  

## P2 INSERT_DELETE

### Positive cases

1. `tests/INSERT_DELETE/Positive/positive_1.*` - Tests INSERT DELETE functionality, focusing on valid index ranges for a list of length n.

  

### Negative cases

1. `tests/INSERT_DELETE/Negative/negative_1.*` - Tests invalid inputs including floats, consecutive spaces, leading/trailing spaces, curly brackets.

  

### Edge cases

1. `tests/INSERT_DELETE/Edge/edge_1.*` - Insert/Delete on an empty list.

2. `tests/INSERT_DELETE/Edge/edge_2.*` - Operations with invalid index range.

3. `tests/INSERT_DELETE/Edge/edge_3.*` - Inserting nothing.

4. `tests/INSERT_DELETE/Edge/edge_4.*` - Deleting an element when the list is empty.

  

## P3 NESTED-LIST

### Positive cases

`tests/Nest/Positive/`

1. `tests/Nest/Positive/positive_1.in` - Tests multiple references to a simple list within nested lists.

  

### Negative cases

`tests/Nest/Negative/`

1. Invalid `VIEW-NEST` commands.

  

### Edge cases

`tests/Nest/Edge/`

1. `tests/Nest/Edge/edge_1.*` - Circular Reference Detection.

2. `tests/Nest/Edge/edge_2.*` - Self-Reference.

3. `tests/Nest/Edge/edge_3.*` - Indirect Reference Deletion.

4. `tests/Nest/Edge/edge_4.*` - Deletion Leading to Simplification.

5. `tests/Nest/Edge/edge_5.*` - Depth Limit tests (1 & 2) focusing on creating nested lists within other nested lists and inserting a list reference into a nested list.




# MY
```
git log --pretty=format:"%H" | while read commit_hash; do if git show $commit_hash | grep --color -P "[\x{4e00}-\x{9fa5}]"; then echo "Found in commit: $commit_hash"; fi; done
```