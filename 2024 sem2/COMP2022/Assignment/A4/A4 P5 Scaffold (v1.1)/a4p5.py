from objects5 import *
import re

#Modify the functions instance_to_formula, assignment_to_solution, solution_to_assignment! Do not change the function names or arguments.
#You may construct additional functions as desired.
#See objects5.py to learn what the objects contain and how to use and construct them.
def instance_to_formula(instance):
    n = instance.n
    rectangles = instance.r
    k = len(rectangles)
    
    clauses = []
    
    variables = {}
    # All possible (x,y)
    for i in range(k):
        Wi, Hi = rectangles[i]
        for x in range(n - Wi + 1):
            for y in range(n - Hi + 1):
                var = Atom(f"X_{i}_{x}_{y}")
                if i not in variables:
                    variables[i] = []
                variables[i].append(var)

    # every rectangle must be in a position
    for i in range(k):
        clause = Disjunction(variables[i])
        clauses.append(clause)
    
    # every rectuange can only be in one position
    for i in range(k):
        positions = variables[i]
        for p in range(len(positions)):
            for q in range(p + 1, len(positions)):
                clause = Disjunction([Negation(positions[p]), Negation(positions[q])])
                clauses.append(clause)
    
    for i in range(k):
        Wi, Hi = rectangles[i]
        for j in range(i + 1, k):
            Wj, Hj = rectangles[j]
            for xi in range(n - Wi + 1):
                for yi in range(n - Hi + 1):
                    for xj in range(n - Wj + 1):
                        for yj in range(n - Hj + 1):
                            if not (xi + Wi <= xj or xj + Wj <= xi or yi + Hi <= yj or yj + Hj <= yi):
                                clause = Disjunction([Negation(Atom(f"X_{i}_{xi}_{yi}")), Negation(Atom(f"X_{j}_{xj}_{yj}"))])
                                clauses.append(clause)
    

    formula = Conjunction(clauses)
    return formula

def assignment_to_solution(formula, assignment):
    positions = []

    for var in assignment.s:
        match = re.match(r"X_(\d+)_(\d+)_(\d+)", var)

        if match:
            rect_index = int(match.group(1))
            x = int(match.group(2))
            y = int(match.group(3))

            while len(positions) <= rect_index:
                positions.append(None)

            positions[rect_index] = (x, y)

    return Solution(positions)

def solution_to_assignment(formula, solution):
    assignment_vars = set()
    
    for i, (x, y) in enumerate(solution.l):
        var_name = f"X_{i}_{x}_{y}"
        assignment_vars.add(var_name)
    
    return Assignment(assignment_vars)


#See run_tests.py for how to test your code locally
