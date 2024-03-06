def generate_permutations(n):
    def backtrack(start, end):
        if start == end:
            print(list) 
        else:
            for i in range(start, end + 1):
                list[start], list[i] = list[i], list[start]  
                backtrack(start + 1, end)  
                list[start], list[i] = list[i], list[start]  

    list = [i for i in range(1, n + 1)] 
    backtrack(0, n - 1)  


generate_permutations(3)
