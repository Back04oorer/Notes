import sys
import re
import random
from collections import defaultdict
from typing import List

class EpsilonTransition:
    pass

class NFA:
    def __init__(self, sigma, states, start, finish):
        self.sigma = sigma
        self.start = start
        self.finish = finish.copy()

        self.transitions = {}
        assert start in states

        for f in finish:
            assert f in states

        for q in states:
            self.transitions[q] = defaultdict(list)

    def add_transition(self, s1, t, s2):
        if t == EpsilonTransition:
            self.transitions[s1][t].append(s2)
            return

        if t not in self.sigma: 
            raise ValueError(f"Transition {t} is not in Sigma")
        self.transitions[s1][t].append(s2)

    def run(self, string) -> bool:
        visited = set()
        return self._run(string, self.start, visited)

    def _run(self, string, state, visited) -> bool:
        if len(string) == 0:
            return state in self.finish or self._dfs_epsilon(state)

        if (string[0], state) in visited:
            return False

        delta = self.transitions[state]

        if EpsilonTransition in delta:
            for q in delta[EpsilonTransition]:
                if self._run(string, q, visited):
                    return True

        c = string[0]
        if c not in delta:
            return False

        for q in delta[c]:
            if self._run(string[1:], q, visited):
                return True
        return False

    def _dfs_epsilon(self, state):
        if EpsilonTransition not in self.transitions[state]:
            return False

        visited = set()
        def _dfs(state):
            if state in visited:
                return False
            visited.add(state)
            delta = self.transitions[state]
            if EpsilonTransition not in delta:
                return False
            for q in delta[EpsilonTransition]:
                if _dfs(q):
                    return True
            return False

        return _dfs(state)


    @staticmethod
    def parse(filename):
        with open(filename, 'r') as f:
            sigma = re.match("^Sigma = (.+)$", f.readline().strip()).group(1).split()
            assert(sigma)

            q : re.Match = re.match("^Q = (.+)$", f.readline().strip()).group(1).split()
            assert(q)

            start = re.match("^start = (.+)$", f.readline().strip()).group(1).split()
            assert(start)

            finish = re.match("^F = (.+)$", f.readline().strip()).group(1).split()
            assert(finish)

            assert(len(start) == 1)
            nfa = NFA(sigma, q, start[0], finish)
            
            while line := f.readline():
                s, t, e = re.match("^(.+) (.+) (.+)$", line).group(1,2,3)
                if t == 'epsilon':
                    t = EpsilonTransition
                assert t in sigma or t == EpsilonTransition
                nfa.add_transition(s, t, e)
            
            return nfa

def compile(regex: str):
    # Academic -> Universal -> Fullmatch
    return re.compile(f'^(?:{regex.replace("(", "(?:")})$')

def read_regex(filename):
    with open(filename, "r") as f:
        return compile(f.read().strip())

def translate_epsilon(group: List[str]):
    try:
        while True:
            idx = group.index("epsilon")
            group[idx] = ''
    except ValueError:
        return

def q1():
    regex = compile(r'(a|(ab))*|b*')
    with open('problem_1_1.txt', 'r') as f:
        accept = f.readline().strip().split(',')
        reject = f.readline().strip().split(',')

        translate_epsilon(accept)
        translate_epsilon(reject)

        for i in range(5):
            if not regex.fullmatch(accept[i]):
                print(f"Q1: {accept[i]} unmatched")
            if regex.fullmatch(reject[i]):
                print(f"Q1: {reject[i]} falsely matched")

with open("problem_2_1.txt", 'w') as w:
    w.write(
"""Sigma = a b
Q = q0 q1 q2
start = q0
F = q2
q0 a q1
q0 b q2
q1 a q1
q1 b q2
q2 a q0
q2 b q0
""")

with open("problem_2_2.txt", "w") as w:
    w.write(
"""Sigma = a b
Q = q0 q1 q2 q3 q4
start = q0
F = q2 q4
q0 a q0
q0 b q0
q0 a q1
q0 b q3
q1 a q2
q2 a q2
q2 b q2
q3 b q4
q4 a q4
q4 b q4
""")

with open("problem_2_3.txt", 'w') as w:
    w.write(
"""Sigma = a b
Q = q0 q1 q2 q3
start = q0
F = q1 q3
q0 a q0
q0 a q1
q0 b q1
q0 b q2
q1 a q1
q1 b q0
q0 b q2
q2 b q3
q3 a q3
q3 b q3
""")

def q2():
    for i in range(1, 4):
        with open(f"problem_2_{i}.txt", 'r') as f:
            nfa = NFA.parse(f"/tmp/q2_{i}_nfa.txt")
            accept = f.readline().strip().split(',')
            reject = f.readline().strip().split(',')

            translate_epsilon(accept)
            translate_epsilon(reject)

            for j in range(5):
                if not nfa.run(accept[j]):
                    print(f"Q2_{i}: {accept[j]} unmatched")
                if nfa.run(reject[j]):
                    print(f"Q2_{i}: {reject[j]} falsely matched")

TEST_CASE = 1000
TEST_MIN_LENGTH = 50
TEST_MAX_LENGTH = 100

def q3_1():
    pass_1 = lambda: "".join(random.choice('b') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH)))
    pass_2 = lambda: "".join(random.choice('a') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH)))
    pass_3 = lambda: "".join(random.choice('ab') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH)))

    nfa = NFA.parse("problem_3_1_nfa.txt")
    regex = read_regex("problem_3_1_re.txt")

    for _ in range(TEST_CASE):
        assert not nfa.run(pass_1())
        assert not regex.fullmatch(pass_1())
        assert nfa.run(pass_2())
        assert regex.fullmatch(pass_2())
        string = pass_3()
        if string.count('a') >= 2:
            assert nfa.run(string)
            assert regex.fullmatch(string)
        else:
            assert not nfa.run(string)
            assert not regex.fullmatch(string)

def q3_2():
    gen = lambda: "".join(random.choice('ab') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH)))

    nfa = NFA.parse("problem_3_2_nfa.txt")
    regex = read_regex("problem_3_2_re.txt")

    for _ in range(TEST_CASE):
        string = gen()
        if string.startswith("baa") and len(string) % 2 == 0:
            assert nfa.run(string)
            assert regex.fullmatch(string)
        else:
            assert not nfa.run(string)
            assert not regex.fullmatch(string)

def q3_3():
    pass_1 = lambda: "".join(random.choice('ab') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH))) + random.choice(["aaaa", "bbbb"])
    pass_2 = lambda: "".join(random.choice('ab') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH)))

    nfa = NFA.parse("problem_3_3_nfa.txt")
    regex = read_regex("problem_3_3_re.txt")
    
    for _ in range(TEST_CASE):
        assert nfa.run(pass_1())
        assert regex.fullmatch(pass_1())
        string = pass_2()
        if string.count(string[-1]) >= 4:
            assert nfa.run(string)
            assert regex.fullmatch(string)
        else:
            assert not nfa.run(string)
            assert not regex.fullmatch(string)

def q3_4():
    pass_1 = lambda: "".join("".join(random.choice('ab') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH))).split("aa"))
    pass_2 = lambda: "".join(random.choice('ab') for _ in range(random.randint(TEST_MIN_LENGTH, TEST_MAX_LENGTH)))

    nfa = NFA.parse("problem_3_4_nfa.txt")
    regex = read_regex("problem_3_4_re.txt")
    
    for _ in range(TEST_CASE):
        string = pass_1()
        if len(string) % 2 == 0:
            assert nfa.run(string)
            assert regex.fullmatch(string)
        else:
            assert not nfa.run(string)
            assert not regex.fullmatch(string)

        string = pass_2()
        if len(string) % 2 == 0 and "aa" not in string:
            assert nfa.run(string)
            assert regex.fullmatch(string)
        else:
            assert not nfa.run(string)
            assert not regex.fullmatch(string)


if __name__ == "__main__":
    q1()
    q2()
    for i in range(1, 5):
        globals()[f"problem_3_{i}"]()
