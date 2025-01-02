from formulas6 import Formula, Disjunction, Conjunction, Negation, Atom, Verum, Falsum
import random #For use in testing - you can use this in your solution if desired but it is not recommended

#Modify the functions part1 and part2! Do not change the function names or arguments.
#You may construct additional functions as desired.

def part1(history):
    if len(history) == 0:
        formula = Disjunction([Atom("r"),Negation(Atom("r"))])
        return (1, formula)

    elif len(history) == 1:

        formula = Atom("r")
        return (1, formula)

    elif len(history) == 2:
        if history[0][2]:
            return history[1][2]

        else:
            return not history[1][2]


def part2(history):
    #Same format as part1

    #In this second example, the program asks guard 1 whether guard 2 tells the truth or not.
    #Based on that answer, it then either asks guard 2 what r is, or what ~r is.
    #It then trusts guard 2's answer to be the value of r.
    formula = Disjunction([Atom("r"),Negation(Atom("r"))])

    if len(history) == 0: #First question
        return (1,formula)
    
    if len(history) == 1: #Second question
        return (2,formula)

    if len(history) == 2:
        return (3,formula)

    if len(history) == 3:
        a1 = history[0][2]
        a2 = history[1][2]
        a3 = history[2][2]

        if a1 == a2:
            return (3,Atom("r"))
        elif a1 == a3:
            return (2,Atom("r"))
        elif a2 == a3:
            return (1,Atom("r"))

    if len(history) == 4:
        a1 = history[0][2]
        a2 = history[1][2]
        a3 = history[2][2]
        answer = history[3][2]

        if a1 == a2:
            if a3:
                return answer
            else:
                return not answer
        elif a1 == a3:
            if a2:
                return answer
            else:
                return not answer
        elif a2 == a3:
            if a1:
                return answer
            else:
                return not answer


if __name__ == "__main__":
    #You may add testing code here (this will not be run during grading)
    #A sample test, corresponding to the public test case on GS, is provided
    #You may wish to add more

    invalid_atoms = [[],["t1","f1"],["t2","f2"],["t3","f3"]] #makes sure a guard can't be asked about themself
    assignment = set(["f1","t2","t3","r"]) #sample test case
    max_questions = 20
    history = []
    for questions in range(max_questions+1):
        response = part1(tuple(history)) #gives the function 'read-only' access to history
        if type(response) == bool:
            print(f"Code output {response}. Expected True. Test {'passed' if response else 'failed'}. Took {questions} questions.")
            break
        else:
            if questions == max_questions:
                print("Code failed to give an answer in {max_questions} questions. Test failed.")
                break
            if not type(response) == tuple:
                print("Test errored. Your function should return either a boolean or tuple. History: {history}")
                break
            if not len(response) == 2:
                print("Test errored. Your function returned a tuple with != 2 elements. History: {history}")
                break
            if not response[0] in (1,2,3):
                print(f"Test errored. Your function returned a tuple with 1st element not 1, 2 or 3. History: {history}")
                break
            if not isinstance(response[1],Formula):
                print(f"Test errored. Your function returned a tuple with 2nd element not a Formula, but a {type(response[1])}. History: {history}")
                break
            if not len(response[1].atoms_used().intersection(invalid_atoms[response[0]])) == 0:
                print(f"Test errored. Your function returned a formula using an invalid atom (asking a guard about themself). History: {history}")
                break
            trueanswer = response[1].evaluate(assignment)
            if [None,"t1","t2","t3"][response[0]] in assignment: #checks whether guard asked tells the truth
                answer = trueanswer
            elif [None,"f1","f2","f3"][response[0]] in assignment:
                answer = not trueanswer
            else: #if neither t_i nor f_i is true, the guard answers randomly
                answer = random.choice([True, False])
#Note: In tests with a random guard you may wish to rerun the test several times to ensure your program always works, regardless of the random guard's answers
            history.append([response[0], response[1], answer])
    print("Test done")
