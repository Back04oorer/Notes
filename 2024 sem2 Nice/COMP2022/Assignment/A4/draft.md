### Q1.1

| p   | q   | $p \rightarrow q$ | $\neg (p \rightarrow q)$ | $\neg (p \rightarrow q) \land p$ |
| --- | --- | ----------------- | ------------------------ | -------------------------------- |
| T   | T   | T                 | F                        | F                                |
| T   | F   | F                 | T                        | T                                |
| F   | T   | T                 | F                        | F                                |
| F   | F   | T                 | F                        | F                                |

### Q1.2
| p   | q   | r   | $p \land q$ | $\neg p \land q$ | $p \rightarrow r$ | $\neg p \rightarrow r$ | $(p \land q)\lor (\neg p \land q)$ | $(p \rightarrow r)\land(\neg p \rightarrow r)$ | Full |
| --- | --- | --- | ----------- | ---------------- | ----------------- | ---------------------- | ---------------------------------- | ---------------------------------------------- | ---- |
| T   | T   | T   | T           | F                | T                 | T                      | T                                  | T                                              | T    |
| T   | T   | F   | T           | F                | F                 | T                      | T                                  | F                                              | F    |
| T   | F   | T   | F           | F                | T                 | T                      | F                                  | T                                              | F    |
| T   | F   | F   | F           | F                | F                 | F                      | F                                  | F                                              | T    |
| F   | T   | T   | F           | T                | T                 | T                      | T                                  | T                                              | T    |
| F   | T   | F   | F           | T                | T                 | F                      | T                                  | F                                              | F    |
| F   | F   | T   | F           | F                | T                 | T                      | F                                  | T                                              | T    |
| F   | F   | F   | F           | F                | T                 | T                      | F                                  | F                                              | T    |

### Q2.1
$$[*] \\= (a \lor b\lor c) \land (a \lor b \lor d) \land (a \lor c \lor d) \land (b \lor c \lor d)$$
### Q2.2
$$
[*] = (a \lor b) \land (a \lor c) \land (b \lor c) \land ( \neg a \lor \neg b \lor \neg c)
$$

### Q3
### function1
$$
x \land ((y \land z) \lor \neg y)
$$
### function2
$$
（x \land y \land \neg z \land w）\lor （x \land y \land z \land \neg w）\lor （\neg x \land y \land \neg z \land \neg w）\lor (x \land \neg y \land \neg z \land \neg w）
$$

### function3


### Q4
WeChat
### Q5.1

| index | 0   | 1   | 2   |
| ----- | --- | --- | --- |
| 0     | 1   | 1   | 3   |
| 1     | 1   | 1   | 3   |
| 2     |     | 2   | 2   |

- $w_i = [1,2,1]$
- $h_i = [2,1,2]$

坐标 $(u,v)$
$[(0,0),(1,2),(2,0)]$

放置:
$R_{i,x,y}$



