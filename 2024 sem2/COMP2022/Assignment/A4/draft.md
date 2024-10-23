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

$$