def opt_strategy(A):
    n = len(A)
    # Compute B[i] = A[0] + ... + A[i-1]
    B = [0] * (n + 1)
    for i in range(n):
        B[i + 1] = B[i] + A[i]
    
    # Find index j0 maximizing B[j + 1] for 0 < j < n
    j0 = max(range(1, n), key=lambda j: B[j + 1])
    # Find index i0 minimizing B[i] for i <= j0
    i0 = min(range(j0 + 1), key=lambda i: B[i])
    # Find index i1 minimizing B[i] for i < n
    i1 = min(range(n), key=lambda i: B[i])
    # Find index j1 maximizing B[j + 1] for i1 <= j < n
    j1 = max(range(i1, n), key=lambda j: B[j + 1])
    
    # Determine the best solution among (i0, j0) and (i1, j1)
    sum0 = B[j0 + 1] - B[i0]
    sum1 = B[j1 + 1] - B[i1]
    if sum0 > sum1:
        return (i0, j0)
    else:
        return (i1, j1)

def max_subarray_sum_with_indices(A):
    if len(A) == 0:
        return 0, -1, -1  # In case the array is empty

    max_sum = current_sum = A[0]
    start = end = 0
    temp_start = 0  # Temporary start index for the current sum

    for i in range(1, len(A)):
        if A[i] > current_sum + A[i]:
            current_sum = A[i]
            temp_start = i
        else:
            current_sum += A[i]

        if current_sum > max_sum:
            max_sum = current_sum
            start = temp_start
            end = i

    return start, end


# Example usage
A = [-1, -1, -3, -10, -4, -7, -2, -5]
print(opt_strategy(A))
print(max_subarray_sum_with_indices(A))
